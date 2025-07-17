package com.bookhaven.interaction_service.dto.request;

import java.time.LocalDateTime;

public record UpdateCommentRequest(
        String id,
        int productId,
        String userId,
        String content,
        String parentCommentId,
        LocalDateTime createdAt
) {
}
