package com.masterprojekat.springserver.services;

import com.masterprojekat.springserver.model.Course;
import com.masterprojekat.springserver.model.Notification;
import com.masterprojekat.springserver.model.Term;
import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.repository.CourseRepository;
import com.masterprojekat.springserver.repository.NotificationRepository;
import com.masterprojekat.springserver.repository.TermRepository;
import com.masterprojekat.springserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    TermRepository termRepository;

    public List<Notification> getAllNotifications(String username) {
        return notificationRepository.findByStudentUsernameOrderByCreatedAtDesc(username);
    }

    public void createNewNotification(int termId, String acceptOrRejectMessage) {
        Term term = termRepository.findById(termId).orElseThrow();
        User student = term.getStudent();
        User professor = term.getProfessor();
        Course course = term.getCourse();

        String message = "Profesor/ka " + professor.getName() + " " + professor.getSurname() + " je " +
                acceptOrRejectMessage + " rezervaciju za kurs " + course.getName() + " za datum " + term.getDate() +
                " i vreme " + term.getTime() + ".";

        Notification notification = new Notification();
        notification.setStudent(student);
        notification.setCourse(course);
        notification.setProfessor(professor);
        notification.setMessage(message);
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    public List<Notification> searchNotifications(String username, String inputSearch) {
        return notificationRepository.searchNotificationsInDatabase(username, inputSearch);
    }
}
