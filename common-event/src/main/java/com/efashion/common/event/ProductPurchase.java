package com.efashion.common.event;

import java.math.BigDecimal;

public record ProductPurchase (
        Integer id,
        String name,
        BigDecimal price,
        Integer quantity
) {
}
