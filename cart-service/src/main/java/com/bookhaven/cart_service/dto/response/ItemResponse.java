package com.bookhaven.cart_service.dto.response;

import java.math.BigDecimal;

public record ItemResponse (
        Integer productId,
        String productName,
        int quantity,
        BigDecimal price,
        BigDecimal promotionalPrice,
        BigDecimal totalPrice,
        String imageUrl
){
}
