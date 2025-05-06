package com.masterprojekat.springserver.controller;

import com.masterprojekat.springserver.model.CourseProgress;
import com.masterprojekat.springserver.services.CoursePogressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseProgressController {
    @Autowired
    CoursePogressService coursePogressService;

    @PutMapping("/courses/{course_id}/markClassHeld")
    public ResponseEntity<CourseProgress> markClassHeld(@PathVariable int courseId, @RequestParam String username) {
        CourseProgress courseProgress = coursePogressService.markClassHeld(courseId, username);
        if(courseProgress == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(courseProgress);
    }

    @GetMapping("/courses/{courseId}/progress/{username}")
    public ResponseEntity<CourseProgress> getCourseProgress(@PathVariable int courseId, @PathVariable String username) {
        CourseProgress courseProgress = coursePogressService.getCourseProgress(courseId, username);
        if(courseProgress == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(courseProgress);
    }
}
