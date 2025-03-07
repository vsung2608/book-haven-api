package com.bookhaven.customer_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Customer {

    @MongoId
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
}
