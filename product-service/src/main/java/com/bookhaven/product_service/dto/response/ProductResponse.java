package com.bookhaven.product_service.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductResponse (
        Integer id,
        String name,
        String description,
        BigDecimal price,
        BigDecimal promotionalPrice,
        Integer qunaity,
        String language,
        LocalDate publishDate,
        String author,
        String publisher,
        String image,
        CategoryResponse category,
        Integer evaluate,
        Integer discount
){ }
