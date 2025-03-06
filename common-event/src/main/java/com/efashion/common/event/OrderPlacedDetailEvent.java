package com.efashion.common.event;

import java.math.BigDecimal;
import java.util.List;

public record OrderPlacedDetailEvent(
        String customerEmail,
        String customerName,
        String orderReference,
        List<ProductPurchase> products,
        BigDecimal amount
) {
}
