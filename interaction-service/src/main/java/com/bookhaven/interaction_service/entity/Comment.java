package com.bookhaven.interaction_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document()
public class Comment {
    @MongoId
    private String id;
    private int productId;
    private String userId;
    private String userName;
    private String userAvatarUrl;
    private String content;
    private int replyCount;
    private String parentCommentId;
    private Instant createdAt;
}
