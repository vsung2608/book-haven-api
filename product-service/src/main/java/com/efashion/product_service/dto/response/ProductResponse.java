package com.efashion.product_service.dto.response;

import java.math.BigDecimal;

public record ProductResponse (
        Integer id,
        String name,
        String description,
        BigDecimal price,
        Integer qunaity,
        Integer categoryId,
        String categoryName,
        String categoryDescription
){ }
