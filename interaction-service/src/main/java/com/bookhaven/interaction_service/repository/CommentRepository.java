package com.bookhaven.interaction_service.repository;

import com.bookhaven.interaction_service.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByParentCommentId(String parentCommentId);

    List<Comment> findByProductId(int productId);
}
