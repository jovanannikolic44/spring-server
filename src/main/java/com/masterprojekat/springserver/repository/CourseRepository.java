package com.masterprojekat.springserver.repository;

import com.masterprojekat.springserver.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Integer> {
    @Query("SELECT course FROM Course course WHERE " +
            "LOWER(course.name) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(course.instrument) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(course.professor.name) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(course.professor.surname) LIKE LOWER(CONCAT('%', :searchText, '%'))")
    List<Course> searchCourseByNameAndInstrumentAndProfessorNameAndSurname(@Param("searchText") String searchText);

}
