package com.efashion.customer_service.dto.response;

import com.efashion.customer_service.entity.Address;

public record CustomerResponse (
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
){
}
