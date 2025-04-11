package com.bookhaven.product_service.dto.response;

import java.math.BigDecimal;

public record ProductLimitedFieldsResponse(
        Integer id,
        String name,
        Integer discount,
        Integer evaluate,
        String image,
        BigDecimal price
) {
}
