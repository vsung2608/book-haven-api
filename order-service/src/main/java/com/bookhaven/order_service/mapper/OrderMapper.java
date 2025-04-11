package com.bookhaven.order_service.mapper;

import com.bookhaven.order_service.dto.request.OrderRequest;
import com.bookhaven.order_service.dto.response.OrderResponse;
import com.bookhaven.order_service.entity.Orders;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Orders toOrder(OrderRequest request){
        return Orders.builder()
                .customerId(request.customerId())
                .paymentMethod(request.paymentMethod())
                .reference(request.reference())
                .totalPrice(request.totalPrice())
                .note(request.note())
                .status(request.status())
                .paymentStatus(request.paymentStatus())
                .shippingFee(request.shippingFee())
                .shippingAddress(request.shippingAddress())
                .build();
    }

    public OrderResponse toOrderResponse(Orders order){
        return null;
    }
}
