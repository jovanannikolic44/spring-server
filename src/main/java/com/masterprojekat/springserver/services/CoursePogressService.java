package com.masterprojekat.springserver.services;

import com.masterprojekat.springserver.model.Course;
import com.masterprojekat.springserver.model.CourseProgress;
import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.repository.CourseProgressRepository;
import com.masterprojekat.springserver.repository.CourseRepository;
import com.masterprojekat.springserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursePogressService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseProgressRepository courseProgressRespository;

    public CourseProgress markClassHeld(int courseId, String username) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        User user = userRepository.findById(username).orElseThrow();
        CourseProgress courseProgress = courseProgressRespository.findByCourseAndUser(course, user);
        if(courseProgress != null) {
            int currentProgress = courseProgress.getProgress();
            int totalClasses = course.getNumberOfClasses();
            if(currentProgress < totalClasses) {
                courseProgress.setProgress(currentProgress + 1);
            }
        }
        return courseProgress;
    }

    public CourseProgress getCourseProgress(int courseId, String username) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        User user = userRepository.findById(username).orElseThrow();
        return courseProgressRespository.findByCourseAndUser(course, user);
    }
}
