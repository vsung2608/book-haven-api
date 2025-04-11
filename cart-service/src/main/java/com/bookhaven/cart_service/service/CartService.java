package com.bookhaven.cart_service.service;

import com.bookhaven.cart_service.dto.response.ItemResponse;
import com.bookhaven.cart_service.entity.Cart;
import com.bookhaven.cart_service.entity.CartItem;
import com.bookhaven.cart_service.mapper.CartItemMapper;
import com.bookhaven.cart_service.repository.CartRepository;
import com.bookhaven.cart_service.repository.http_client.ProductClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository repository;
    private final ProductClient productClient;
    private final CartItemMapper mapper;
    private final CartRepository cartRepository;

    public Flux<ItemResponse> addToCart(String customerId, int productId, int quantity) {
        return repository.findByCustomerId(customerId)
                .defaultIfEmpty(Cart.builder().customerId(customerId).items(new ArrayList<>()).build())
                .flatMap(cart ->
                        Flux.fromIterable(cart.getItems())
                                .filter(cartItem -> productId == cartItem.getProductId())
                                .next()
                                .defaultIfEmpty(new CartItem())
                                .flatMap(item -> productClient.getProuctById(productId)
                                        .map(prd -> {
                                            item.setProductId(productId);
                                            item.setProductName(prd.name());
                                            item.setQuantity(item.getQuantity() + quantity);
                                            item.setPrice(prd.price());
                                            item.setImageUrl(prd.image());
                                            item.setPromotionalPrice(BigDecimal.valueOf(((100 - (double) prd.discount()) / 100) * prd.price().doubleValue()));

                                            if (!cart.getItems().contains(item)) {
                                                cart.getItems().add(item);
                                            }
                                            return cart;
                                        })
                                )
                )
                .flatMap(repository::save)
                .flatMapMany(cart -> Flux.fromIterable(cart.getItems()))
                .map(mapper::toItemResponse);
    }

    public Flux<ItemResponse> getAllItemsInCart(String customerId) {
        return repository.findByCustomerId(customerId)
                .defaultIfEmpty(
                        Cart.builder().customerId(customerId).items(new ArrayList<>()).build()
                )
                .flatMapMany(cart -> Flux.fromIterable(cart.getItems()))
                .map(mapper::toItemResponse);
    }

    public Flux<ItemResponse> removeItemFromCart(String customerId, int productId) {
        return repository.findByCustomerId(customerId)
                .defaultIfEmpty(Cart.builder().customerId(customerId).items(new ArrayList<>()).build())
                .flatMap(cart -> {
                    boolean removed = cart.getItems().removeIf(item -> item.getProductId()== productId);
                    if (!removed) {
                        return Mono.error(new EntityNotFoundException("Item with ID :: %d not found in cart".formatted(productId)));
                    }
                    return repository.save(cart);
                })
                .flatMapMany(cart -> Flux.fromIterable(cart.getItems()))
                .map(mapper::toItemResponse);
    }

    public Flux<ItemResponse> clearCart(String customerId) {
        return repository.findByCustomerId(customerId)
                .flatMap(cart -> {
                    cart.getItems().clear();
                    return repository.save(cart);
                })
                .flatMapMany(cart -> Flux.fromIterable(cart.getItems()))
                .map(mapper::toItemResponse);
    }
}
