package com.bookhaven.cart_service.mapper;

import com.bookhaven.cart_service.dto.response.ItemResponse;
import com.bookhaven.cart_service.entity.CartItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CartItemMapper {

    public ItemResponse toItemResponse(final CartItem cartItem) {
        return new ItemResponse(
                cartItem.getProductId(),
                cartItem.getProductName(),
                cartItem.getQuantity(),
                cartItem.getPrice(),
                BigDecimal.valueOf(cartItem.getPromotionalPrice().doubleValue() * cartItem.getQuantity()),
                cartItem.getPromotionalPrice(),
                cartItem.getImageUrl()
        );
    }

}
