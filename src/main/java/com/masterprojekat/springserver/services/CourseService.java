package com.masterprojekat.springserver.services;

import com.masterprojekat.springserver.model.Course;
import com.masterprojekat.springserver.repository.CourseRepository;
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
}
