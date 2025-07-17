package com.bookhaven.interaction_service.controller;

import com.bookhaven.interaction_service.dto.request.NewCommentRequest;
import com.bookhaven.interaction_service.dto.response.CommentResponse;
import com.bookhaven.interaction_service.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentConstroller {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@RequestBody NewCommentRequest request) {
        return ResponseEntity.ok(commentService.createComment(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable int id) {
        return ResponseEntity.ok(commentService.getComment(id));
    }

    @GetMapping("/reply/{id}")
    public ResponseEntity<List<CommentResponse>> getReplyComments(@PathVariable String id) {
        return ResponseEntity.ok(commentService.getReplyComment(id));
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
    }
}
