package com.efashion.notification_service.email;

import com.efashion.common.event.OrderPlacedDetailEvent;
import com.efashion.common.event.PaymentSuccessEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final TemplateEngine templateEngine;

    public String setOrderPlacedNotificationEmail(OrderPlacedDetailEvent event, EmailTemplate emailTemplate){
        Context context = new Context();
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", event.customerName());
        variables.put("totalAmount", event.amount());
        variables.put("orderReference", event.orderReference());
        variables.put("products", event.products());
        context.setVariables(variables);
        return templateEngine.process(
                emailTemplate.getTemplate(),
                context
        );
    }

    public String setPaymentSuccessNotificationEmail(PaymentSuccessEvent event, EmailTemplate emailTemplate){
        Context context = new Context();
        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", event.customerName());
        variables.put("amount", event.amount());
        variables.put("orderReference", event.orderConference());
        context.setVariables(variables);
        return templateEngine.process(emailTemplate.getTemplate(), context);
    }
}
