package com.efashion.common.dto;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
){ }
