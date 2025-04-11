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
@SequenceGenerator(
        name = "product_seq",
        sequenceName = "product_seq",
        allocationSize = 1
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
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
    private Integer evaluate;
    private Integer discount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
