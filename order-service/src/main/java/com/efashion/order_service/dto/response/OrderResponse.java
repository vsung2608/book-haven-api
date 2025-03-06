package com.efashion.order_service.dto.response;

import java.math.BigDecimal;
import java.util.List;


public record OrderResponse (
        Integer id,
        String reference,
        BigDecimal totalPrice,
        List<OrderItemResponse> orderItems
) {}
