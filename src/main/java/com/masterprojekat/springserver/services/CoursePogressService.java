package com.masterprojekat.springserver.services;

import com.masterprojekat.springserver.model.*;
import com.masterprojekat.springserver.repository.CourseProgressRepository;
import com.masterprojekat.springserver.repository.CourseRepository;
import com.masterprojekat.springserver.repository.TermRepository;
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
    @Autowired
    private TermRepository termRepository;

    public CourseProgress markClassHeld(int termId) {
        Term term = termRepository.findById(termId).orElseThrow();
        Course course = term.getCourse();
        User student = term.getStudent();
        CourseProgress courseProgress = courseProgressRespository.findByCourseAndUser(course, student);
        if(courseProgress != null) {
            int currentProgress = courseProgress.getProgress();
            int totalClasses = course.getNumberOfClasses();
            if(currentProgress < totalClasses) {
                courseProgress.setProgress(currentProgress + 1);
            }
            term.setStatus(TermStatus.ODRZAN);
            termRepository.save(term);
        }
        return courseProgress;
    }

    public Term markClassNotHeld(int termId) {
        Term term = termRepository.findById(termId).orElseThrow();
        term.setStatus(TermStatus.NIJE_ODRZAN);
        termRepository.save(term);
        return term;
    }

    public CourseProgress getCourseProgress(int courseId, String username) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        User user = userRepository.findById(username).orElseThrow();
        return courseProgressRespository.findByCourseAndUser(course, user);
    }
}
