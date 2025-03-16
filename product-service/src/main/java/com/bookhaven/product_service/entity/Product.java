package com.bookhaven.product_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Validated
public class Product {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private String language;
    private LocalDate publishDate;
    private String author;
    private String publisher;
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
