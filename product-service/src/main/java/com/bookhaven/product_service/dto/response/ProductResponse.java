package com.bookhaven.product_service.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
        List<String> image,
        CategoryResponse category,
        Integer evaluate,
        Integer discount,
        List<ProductLimitedFieldsResponse> relatedProducts
){ }
