package com.bookhaven.notification_service.service;

import com.bookhaven.common.event.OrderPlacedDetailEvent;
import com.bookhaven.common.event.PaymentSuccessEvent;
import com.bookhaven.notification_service.dto.request.EmailRequest;
import com.bookhaven.notification_service.dto.request.Recipient;
import com.bookhaven.notification_service.dto.request.Sender;
import com.bookhaven.notification_service.dto.response.EmailResponse;
import com.bookhaven.notification_service.entity.Notification;
import com.bookhaven.notification_service.entity.NotificationType;
import com.bookhaven.notification_service.email.EmailService;
import com.bookhaven.notification_service.email.EmailTemplate;
import com.bookhaven.notification_service.repository.EmailClient;
import com.bookhaven.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final EmailClient emailClient;
    private final EmailService emailService;
    private final NotificationRepository notificationRepository;

    @Value("${application.brevo.api-key}")
    private String apiKey;

    public EmailResponse sendOrderPlacedEmail(OrderPlacedDetailEvent event){
        EmailRequest request = EmailRequest.builder()
                .sender(Sender.builder()
                        .email("vsung2608@gmail.com")
                        .name("Sung NV")
                        .build())
                .to(List.of(Recipient.builder()
                                .email(event.customerEmail())
                                .name(event.customerName())
                        .build()))
                .htmlContent(emailService.setOrderPlacedNotificationEmail(event, EmailTemplate.ORDER_CONFIRMATION))
                .subject("hihi no content here")
                .build();

        notificationRepository.save(Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                .build());
        return emailClient.send(apiKey, request);
    }

    public EmailResponse sendPaymentSuccessEmail(PaymentSuccessEvent event){
        EmailRequest request = EmailRequest.builder()
                .sender(Sender.builder()
                        .email("vsung2608@gmail.com")
                        .name("Sung NV")
                        .build())
                .to(List.of(Recipient.builder()
                        .email(event.customerEmail())
                        .name(event.customerName())
                        .build()))
                .htmlContent(emailService.setPaymentSuccessNotificationEmail(event, EmailTemplate.ORDER_CONFIRMATION))
                .subject("hihi no content here")
                .build();

        notificationRepository.save(Notification.builder()
                .type(NotificationType.PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .build());
        return emailClient.send(apiKey, request);
    }
}
