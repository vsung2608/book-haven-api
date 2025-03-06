package com.efashion.order_service.mapper;

import com.efashion.order_service.dto.request.OrderRequest;
import com.efashion.order_service.dto.response.OrderResponse;
import com.efashion.order_service.entity.Orders;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Orders toOrder(OrderRequest request){
        return Orders.builder()
                .customerId(request.customerId())
                .paymentMethod(request.paymentMethod())
                .reference(request.reference())
                .totalPrice(request.totalPrice())
                .build();
    }

    public OrderResponse toOrderResponse(Orders order){
        return null;
    }
}
