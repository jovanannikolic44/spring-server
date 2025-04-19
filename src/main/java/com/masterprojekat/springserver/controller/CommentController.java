package com.masterprojekat.springserver.controller;

import com.masterprojekat.springserver.model.Comment;
import com.masterprojekat.springserver.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comments/save-comment")
    public ResponseEntity<Void> saveComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/comments/get-all-comments-for-course")
    public ResponseEntity<List<Comment>> getAllCommentsForCourse(@RequestParam int courseId) {
        List<Comment> commentsList = commentService.getAllCommentsForCourse(courseId);
        return ResponseEntity.ok(commentsList);
    }
}
