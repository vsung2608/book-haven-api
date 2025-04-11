package com.bookhaven.cart_service.controller;

import com.bookhaven.cart_service.dto.response.ItemResponse;
import com.bookhaven.cart_service.service.CartService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Validated
@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    public Flux<ItemResponse> addItem(
            @RequestParam(value = "customerId") @NotBlank(message = "Id nguoi dung khong duoc de trong") String customerId,
            @RequestParam(value = "productId") @Min(value = 1, message = "Id san pham khong hop le") int productId,
            @RequestParam(value = "quantity") @Min(value = 1, message = "Số lượng phải lớn hơn 0") int quantity
    ) {
        return cartService.addToCart(customerId, productId, quantity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flux<ItemResponse>> getAllItems(@PathVariable String id) {
        return ResponseEntity.ok(cartService.getAllItemsInCart(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Flux<ItemResponse>> deleteCart(@PathVariable String id) {
        return ResponseEntity.ok(cartService.clearCart(id));
    }

    @DeleteMapping("/item")
    public ResponseEntity<Flux<ItemResponse>> deleteItem(@RequestParam("customerId") String id, @RequestParam("productId") int itemId) {
        return ResponseEntity.ok(cartService.removeItemFromCart(id, itemId));
    }
}
