package com.bookhaven.payment_service.service;

import com.bookhaven.payment_service.dto.PaymentRequest;
import com.bookhaven.payment_service.event.PaymentSuccessEvent;
import com.bookhaven.payment_service.mapper.PaymentMapper;
import com.bookhaven.payment_service.repository.PaymentRepositoty;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepositoty paymentRepositoty;
    private final PaymentMapper paymentMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void createPayment(PaymentRequest payment) {
        paymentRepositoty.save(paymentMapper.toPayment(payment));

        PaymentSuccessEvent event = new PaymentSuccessEvent(
                payment.customerName(),
                payment.customerEmail(),
                payment.amount(),
                payment.orderReference()
        );

        kafkaTemplate.send("paymentTopics", event);
    }
}
