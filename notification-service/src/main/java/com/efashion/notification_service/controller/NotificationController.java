package com.efashion.notification_service.controller;

import com.efashion.common.event.OrderPlacedDetailEvent;
import com.efashion.common.event.PaymentSuccessEvent;
import com.efashion.notification_service.dto.response.EmailResponse;
import com.efashion.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @KafkaListener(topics = "orderTopics")
    public ResponseEntity<EmailResponse> consumerOrderPlacedEvent(OrderPlacedDetailEvent event) {
        return ResponseEntity.ok(notificationService.sendOrderPlacedEmail(event));
    }

    @KafkaListener(topics = "paymentTopics")
    public ResponseEntity<EmailResponse> consumerPaymentSuccessEvent(PaymentSuccessEvent event) {
        return ResponseEntity.ok(notificationService.sendPaymentSuccessEmail(event));
    }
}
