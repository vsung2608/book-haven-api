package com.bookhaven.common.event;

import java.math.BigDecimal;

public record PaymentSuccessEvent (
        String customerEmail,
        String customerName,
        BigDecimal amount,
        String orderConference
) {
}
