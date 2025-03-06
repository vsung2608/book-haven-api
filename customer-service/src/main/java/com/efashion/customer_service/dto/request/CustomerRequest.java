package com.efashion.customer_service.dto.request;

import com.efashion.customer_service.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        @NotNull(message = "Customer's first name is required")
        String firstName,
        @NotNull(message = "Customer's last name is required")
        String lastName,
        @NotNull(message = "Customer's email is required")
        @Email(message = "Email is not a valid email")
        String email,
        Address address
) {
}
