package com.masterprojekat.springserver.repository;

import com.masterprojekat.springserver.model.Course;
import com.masterprojekat.springserver.model.CourseProgress;
import com.masterprojekat.springserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseProgressRepository extends JpaRepository<CourseProgress, Integer> {
    CourseProgress findByCourseAndUser(Course course, User user);
    List<CourseProgress> findAllByUser(User user);
}
