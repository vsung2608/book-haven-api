package com.bookhaven.interaction_service.service;

import com.bookhaven.interaction_service.dto.request.NewCommentRequest;
import com.bookhaven.interaction_service.dto.request.UpdateCommentRequest;
import com.bookhaven.interaction_service.dto.response.CommentResponse;
import com.bookhaven.interaction_service.entity.Comment;
import com.bookhaven.interaction_service.mapper.CommmentMapper;
import com.bookhaven.interaction_service.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;
    private final CommmentMapper mapper;
    private final MongoTemplate mongoTemplate;

    public CommentResponse createComment(NewCommentRequest request) {
        if(request.parentCommentId() != null){
            incrementReplyCount(request.parentCommentId());
        }
         return mapper.CommentToCommentResponse(repository.save(mapper.CommentRequestToComment(request)));
    }

    public CommentResponse updateComment(UpdateCommentRequest request) {
        var comment = repository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Khong tim thay binh luan co ID::%s".formatted(request.id())));

        comment.setContent(request.content());
        return mapper.CommentToCommentResponse(repository.save(comment));
    }

    public List<CommentResponse> getComment(int productId){
        return repository.findByProductId(productId).stream()
                .map(mapper::CommentToCommentResponse)
                .toList();
    }

    public List<CommentResponse> getReplyComment(String parentCommentId){
        return repository.findByParentCommentId(parentCommentId).stream()
                .map(mapper::CommentToCommentResponse)
                .toList();
    }

    public void incrementReplyCount(String commentId) {
        Query query = new Query(Criteria.where("_id").is(commentId));
        Update update = new Update().inc("replyCount", 1);
        mongoTemplate.updateFirst(query, update, Comment.class);
    }

    public void deleteComment(String commentId) {
        var comment = repository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Khong tim thay binh luan co ID::%s".formatted(commentId)));

        repository.delete(comment);
    }
}
