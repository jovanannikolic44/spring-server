package com.masterprojekat.springserver.services;

import com.masterprojekat.springserver.model.Comment;
import com.masterprojekat.springserver.model.Course;
import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.repository.CommentRepository;
import com.masterprojekat.springserver.repository.CourseRepository;
import com.masterprojekat.springserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    public void addComment(Comment comment) {
        User author = userRepository.findById(comment.getAuthor().getUsername()).orElseThrow();
        Course course = courseRepository.findById(comment.getCourse().getCourseId()).orElseThrow();
        comment.setAuthor(author);
        comment.setCourse(course);
        course.getComments().add(comment);
        commentRepository.save(comment);
        courseRepository.save(course);
    }

    public List<Comment> getAllCommentsForCourse(int courseId) {
        return commentRepository.findByCourse_CourseId(courseId);
    }
}
