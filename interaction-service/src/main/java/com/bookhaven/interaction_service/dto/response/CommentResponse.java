package com.bookhaven.interaction_service.dto.response;

import java.time.LocalDateTime;

public record CommentResponse(
        String id,
        int productId,
        String userId,
        String content,
        String userName,
        String userAvatarUrl,
        int replyCount,
        String parentCommentId,
        String createdAt
) {
}
