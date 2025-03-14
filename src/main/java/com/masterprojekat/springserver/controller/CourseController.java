package com.masterprojekat.springserver.controller;

import com.masterprojekat.springserver.model.Course;
import com.masterprojekat.springserver.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("/course/get-all")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/course/search")
    public ResponseEntity<List<Course>> searchCourses(@RequestParam String searchText) {
        List<Course> foundCourses = courseService.search(searchText);
        if (foundCourses != null) {
            return ResponseEntity.ok(foundCourses);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
