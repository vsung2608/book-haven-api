package com.bookhaven.interaction_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document()
public class Rating {
    @MongoId
    private String id;
    private int productId;
    private String userId;
    private int score;
    private LocalDateTime createdAt = LocalDateTime.now();
}
