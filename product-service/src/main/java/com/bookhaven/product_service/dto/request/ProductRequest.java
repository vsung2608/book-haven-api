package com.bookhaven.product_service.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
        @NotNull
        private String name;
        @NotNull
        private String description;
        @Positive
        private BigDecimal price;
        @NotNull
        private Integer categoryId;
        private Integer quantity;
        private String language;
        private LocalDate publishDate;
        private String author;
        private String publisher;
        private Integer evaluate;
        private Integer discount;
}

