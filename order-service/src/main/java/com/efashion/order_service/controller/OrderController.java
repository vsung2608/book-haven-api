package com.efashion.order_service.controller;

import com.efashion.order_service.dto.request.OrderRequest;
import com.efashion.order_service.dto.response.OrderResponse;
import com.efashion.order_service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @CircuitBreaker(name="inventory", fallbackMethod = "fallBackPlaceOrder")
    @TimeLimiter(name="inventory")
    @Retry(name="inventory")
    public CompletableFuture<ResponseEntity<OrderResponse>> placeOrder(@RequestBody OrderRequest orderRequest) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(orderService.placeOrder(orderRequest)));
    }

    public CompletableFuture<String> fallBackPlaceOrder(@RequestBody OrderRequest orderRequest, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(() -> "Ops! something went wrong, please try again after some time");
    }
}
