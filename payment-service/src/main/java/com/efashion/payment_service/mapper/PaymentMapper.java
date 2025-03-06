package com.efashion.payment_service.mapper;

import com.efashion.payment_service.dto.PaymentRequest;
import com.efashion.payment_service.entity.Payment;
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
