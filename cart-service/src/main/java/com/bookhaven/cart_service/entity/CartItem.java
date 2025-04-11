package com.bookhaven.cart_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Integer productId;
    private String productName;
    private int quantity;
    private BigDecimal price;
    private BigDecimal promotionalPrice;
    private String imageUrl;
}
