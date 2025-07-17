package com.bookhaven.interaction_service.dto.request;

public record NewCommentRequest(
        int productId,
        String userId,
        String content,
        String userName,
        String userAvatarUrl,
        String parentCommentId
){
}
