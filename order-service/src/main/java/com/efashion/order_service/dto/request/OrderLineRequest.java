package com.efashion.order_service.dto.request;

public record OrderLineRequest(
        Integer orderId,
        Integer quantity,
        Integer proCode
) {
}
