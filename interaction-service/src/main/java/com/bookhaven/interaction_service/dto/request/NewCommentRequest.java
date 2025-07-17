package com.bookhaven.interaction_service.dto.request;

public record CommentRequest (
        int productId,
        String userId,
        String content
){
}
