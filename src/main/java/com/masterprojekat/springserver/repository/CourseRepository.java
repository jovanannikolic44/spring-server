package com.masterprojekat.springserver.repository;

import com.masterprojekat.springserver.model.Course;
import com.masterprojekat.springserver.model.CourseStatus;
import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.model.UserAccountStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("SELECT course FROM Course course WHERE course.status = 'PRIHVACEN' AND (" +
            "LOWER(course.name) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(course.instrument) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(course.professor.name) LIKE LOWER(CONCAT('%', :searchText, '%')) OR " +
            "LOWER(course.professor.surname) LIKE LOWER(CONCAT('%', :searchText, '%')))")
    List<Course> searchCourseByNameAndInstrumentAndProfessorNameAndSurname(@Param("searchText") String searchText);

    @Query("SELECT course FROM Course course WHERE course.instrument IN :instruments AND course.status = 'PRIHVACEN'")
    List<Course> findByInstrumentIn(@Param("instruments") Set<String> instruments);

    @Query("SELECT course FROM Course course WHERE course.status = 'PRIHVACEN' ORDER BY course.rating DESC")
    List<Course> findFiveCoursesByOrderByRatingDesc(Pageable pageable);

    @Query("SELECT course FROM Course course WHERE course.status = 'PRIHVACEN' ORDER BY course.price ASC")
    List<Course> findFiveCoursesByOrderByPriceDesc(Pageable pageable);

    List<Course> findByProfessorAndStatus(User professor, CourseStatus status);
    List<Course> findByStatus(CourseStatus status);
}
