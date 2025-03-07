package com.bookhaven.common.dto;

import java.math.BigDecimal;

public record PurchaseResponse(
        Integer id,
        String name,
        Integer quantity,
        Boolean available,
        BigDecimal price
) {
}
