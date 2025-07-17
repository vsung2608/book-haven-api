package com.bookhaven.interaction_service.mapper;

import com.bookhaven.interaction_service.dto.request.NewCommentRequest;
import com.bookhaven.interaction_service.dto.response.CommentResponse;
import com.bookhaven.interaction_service.entity.Comment;
import com.bookhaven.interaction_service.util.TimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommmentMapper {
    private final TimeFormatter timeFormatter;

    public Comment CommentRequestToComment(NewCommentRequest request) {
        return Comment.builder()
                .userId(request.userId())
                .content(request.content())
                .productId(request.productId())
                .userName(request.userName())
                .userAvatarUrl(request.userAvatarUrl())
                .parentCommentId(request.parentCommentId())
                .createdAt(Instant.now())
                .replyCount(0)
                .build();
    }

    public CommentResponse CommentToCommentResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getProductId(),
                comment.getUserId(),
                comment.getContent(),
                comment.getUserName(),
                comment.getUserAvatarUrl(),
                comment.getReplyCount(),
                comment.getParentCommentId(),
                timeFormatter.format(comment.getCreatedAt())
        );
    }
}
