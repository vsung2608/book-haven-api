package com.bookhaven.payment_service.dto;

import com.bookhaven.payment_service.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        String customerName,
        String customerEmail
) {
}
