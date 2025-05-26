package com.masterprojekat.springserver.services;

import com.masterprojekat.springserver.model.Course;
import com.masterprojekat.springserver.model.CourseStatus;
import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.repository.CourseRepository;
import com.masterprojekat.springserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Course> getAllCourses() {
        List<Course> users = new ArrayList<>();
        Streamable.of(courseRepository.findAll()).forEach(users::add);
        return users;
    }

    public List<Course> search(String searchText) {
        return courseRepository.searchCourseByNameAndInstrumentAndProfessorNameAndSurname(searchText);
    }

    public List<Course> getByPreferences(Set<String> preferences) {
        return courseRepository.findByInstrumentIn(preferences);
    }

    public List<Course> getFiveBestRatedCourses() {
        Pageable pageable = PageRequest.of(0,5);
        return courseRepository.findFiveCoursesByOrderByRatingDesc(pageable);
    }

    public List<Course> getFiveCheapestCourses() {
        Pageable pageable = PageRequest.of(0,5);
        return courseRepository.findFiveCoursesByOrderByPriceDesc(pageable);
    }

    public void saveRating(int courseId, float rating) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        float totalSumRatings = course.getTotalSumRatings();
        float numberOfRatings = course.getNumberOfRatings();
        totalSumRatings += rating;
        numberOfRatings++;
        float newRating = totalSumRatings / numberOfRatings;
        course.setRating(newRating);
        course.setTotalSumRatings(totalSumRatings);
        course.setNumberOfRatings(numberOfRatings);
        courseRepository.save(course);
    }

    public float getRating(int courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        return course.getRating();
    }

    public List<Course> getAllProfessorsCourses(String professorUsername) {
        User professor = userRepository.findById(professorUsername).orElseThrow();
        return courseRepository.findByProfessor(professor);
    }

    public Course saveCourse(Course newCourse) {
        newCourse.setStatus(CourseStatus.ZAHTEV_POSLAT);
        return courseRepository.save(newCourse);
    }
}
