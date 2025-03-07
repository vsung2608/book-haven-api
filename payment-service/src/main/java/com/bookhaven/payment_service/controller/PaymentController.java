package com.bookhaven.payment_service.controller;

import com.bookhaven.payment_service.dto.PaymentRequest;
import com.bookhaven.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Void> createPayment(@RequestBody PaymentRequest request) {
        paymentService.createPayment(request);
        return ResponseEntity.ok().build();
    }
}
