package com.masterprojekat.springserver.services;

import com.masterprojekat.springserver.model.Course;
import com.masterprojekat.springserver.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
