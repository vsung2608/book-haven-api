package com.efashion.notification_service.email;

import lombok.Getter;

@Getter
public enum EmailTemplate {
    PAYMENT_CONFIRMATION("payment-confirmation", "Payment successfully processed"),
    ORDER_CONFIRMATION("order-placed", "Order placed detail")
    ;

    private final String template;
    private final String subject;

    EmailTemplate(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
