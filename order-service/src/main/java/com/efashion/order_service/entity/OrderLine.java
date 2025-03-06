package com.efashion.order_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer proCode;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "oder_line_id", nullable = false)
    private Orders order;

}
