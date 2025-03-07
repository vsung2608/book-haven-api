package com.bookhaven.product_service.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest (
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product desciption is required")
        String description,
        @Positive(message = "Product price should be positive")
        BigDecimal price,
        @NotNull(message = "Product category is required")
        Integer categoryId
) {
}
