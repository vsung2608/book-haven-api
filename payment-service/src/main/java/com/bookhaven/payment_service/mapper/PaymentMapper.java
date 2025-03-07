package com.bookhaven.payment_service.mapper;

import com.bookhaven.payment_service.dto.PaymentRequest;
import com.bookhaven.payment_service.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest request){
        return Payment.builder()
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .orderId(request.orderId())
                .build();
    }
}
