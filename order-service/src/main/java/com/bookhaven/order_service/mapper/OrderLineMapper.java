package com.bookhaven.order_service.mapper;

import com.bookhaven.order_service.dto.request.OrderLineRequest;
import com.bookhaven.order_service.entity.OrderLine;
import com.bookhaven.order_service.entity.Orders;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .order(Orders.builder()
                        .id(request.orderId())
                        .build())
                .quantity(request.quantity())
                .proCode(request.proCode())
                .build();
    }
}
