package com.bookhaven.customer_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;

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
    private LocalDate dateOfBirth;
    private boolean male;
    private Address address;
    private Status status;
    private Integer activity;
    private String avatar;
    @CreatedDate
    private LocalDate createdDate;
    @LastModifiedDate
    private LocalDate lastModifiedDate;
    private LocalDate deletedDate;
    private String notes;
}
