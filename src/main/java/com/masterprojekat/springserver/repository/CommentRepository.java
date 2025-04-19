package com.masterprojekat.springserver.repository;
import com.masterprojekat.springserver.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByCourse_CourseId(int courseId);
}
