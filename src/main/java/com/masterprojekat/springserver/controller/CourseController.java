package com.masterprojekat.springserver.controller;

import com.masterprojekat.springserver.model.Course;
import com.masterprojekat.springserver.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

@RestController
public class CourseController {
    @Autowired
    CourseService courseService;
    private String uploadsPath = "C:\\Users\\Jovana\\Desktop\\Master\\Projects\\spring-server\\uploads\\";

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

    @GetMapping("/course/get-image")
    public ResponseEntity<Resource> getCourseImage(@RequestParam String imageName) {
        String courseImageUploadPath = uploadsPath + "courses_pictures";
        Path filePath = Paths.get(courseImageUploadPath).resolve(imageName);
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.status(HttpStatus.OK).body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/course/by-preference")
    public ResponseEntity<List<Course>> getCoursesByPreference(@RequestBody Set<String> preferences) {
        List<Course> courses = courseService.getByPreferences(preferences);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/course/best-rated")
    public ResponseEntity<List<Course>> getBestRatedCourses() {
        List<Course> courses = courseService.getFiveBestRatedCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/course/cheapest")
    public ResponseEntity<List<Course>> getCheapestCourses() {
        List<Course> courses = courseService.getFiveCheapestCourses();
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/course/save-rating")
    public ResponseEntity<Void> saveCourseRating(@RequestParam int courseId, float rating) {
        courseService.saveRating(courseId, rating);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/course/get-rating")
    public ResponseEntity<Float> getCourseRating(@RequestParam int courseId) {
        float rating = courseService.getRating(courseId);
        return ResponseEntity.ok(rating);
    }

    @GetMapping("/course/professors/{professorUsername}")
    public ResponseEntity<List<Course>> getAllCoursesByProfessor(@PathVariable String professorUsername) {
        List<Course> allCourses = courseService.getAllProfessorsCourses(professorUsername);
        if(allCourses == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(allCourses);
    }

    @PostMapping("/course/add-new")
    public ResponseEntity<?> addCourse(@RequestParam("name") String name, @RequestParam("price") float price,
                                       @RequestParam("professorUsername") String professorUsername,
                                       @RequestParam("level") String level, @RequestParam("instrument") String instrument,
                                       @RequestParam("description") String description, @RequestParam("content") String content,
                                       @RequestParam("numberOfClasses") int numberOfClasses, @RequestParam("image") MultipartFile image
    ) {
        String imageUploadPath = uploadsPath + "courses_pictures";
        File uploadDir = new File(imageUploadPath);

        if (!uploadDir.exists() && !uploadDir.mkdirs()) {
            System.err.println("Greska! Neuspesno kreiranje direktorijuma!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Greska! Neuspesno kreiranje direktorijuma!");
        }


        String fileName = "course_" + System.currentTimeMillis() + image.getOriginalFilename();
        File destination = new File(uploadDir, fileName);

        try {
            image.transferTo(destination);
            String imagePath = "/uploads/course_pictures/" + fileName;
            courseService.saveCourseWithImage(name, price, professorUsername, level, instrument,
                    description, content, numberOfClasses, imagePath);

            return ResponseEntity.ok().body("Kurs je uspesno dodat.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Greska pri cuvanju slike kursa.");
        }
    }

    @PutMapping("/course/update-info")
    public ResponseEntity<Course> updateCourseInfo(@RequestBody Course course) {
        try {
            Course updatedCourse = courseService.updateCourse(course);
            if (updatedCourse == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedCourse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
