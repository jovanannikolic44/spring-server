package com.masterprojekat.springserver.repository;

import com.masterprojekat.springserver.model.Course;
import com.masterprojekat.springserver.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("SELECT course FROM Course course WHERE " +
            "LOWER(course.name) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(course.instrument) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(course.professor.name) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(course.professor.surname) LIKE LOWER(CONCAT('%', :searchText, '%'))")
    List<Course> searchCourseByNameAndInstrumentAndProfessorNameAndSurname(@Param("searchText") String searchText);
    List<Course> findByInstrumentIn(Set<String> instruments);

    @Query("SELECT course FROM Course course ORDER BY course.rating DESC")
    List<Course> findFiveCoursesByOrderByRatingDesc(Pageable pageable);
    @Query("SELECT course FROM Course course ORDER BY course.price ASC")
    List<Course> findFiveCoursesByOrderByPriceDesc(Pageable pageable);

    List<Course> findByProfessor(User professor);
}
