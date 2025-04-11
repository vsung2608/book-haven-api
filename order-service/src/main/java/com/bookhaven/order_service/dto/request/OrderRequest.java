package com.bookhaven.order_service.dto.request;

import com.bookhaven.common.dto.PurchaseRequest;
import com.bookhaven.order_service.entity.PaymentMethod;
import com.bookhaven.order_service.entity.PaymentStatus;
import com.bookhaven.order_service.entity.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        String reference,
        @Positive(message = "Order price should be positive")
        BigDecimal totalPrice,
        @NotNull(message = "Payment method should be precised")
        PaymentMethod paymentMethod,
        Status status,
        PaymentStatus paymentStatus,
        String note,
        BigDecimal shippingFee,
        String shippingAddress,
        @NotNull(message = "Customer should be present")
        @NotEmpty(message = "Customer should be present")
        @NotBlank(message = "Customer should be present")
        String customerId,
        @NotEmpty(message = "You should at least purchase one product")
        List<PurchaseRequest> orderLines
) { }
