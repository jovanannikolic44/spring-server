package com.masterprojekat.springserver.repository;

import com.masterprojekat.springserver.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByStudentUsernameOrderByCreatedAtDesc(String username);
    @Query("SELECT notification FROM Notification notification " +
            "WHERE notification.student.username = :username AND " +
            "(notification.message LIKE %:inputSearch% OR " +
            "notification.professor.username LIKE %:inputSearch% OR " +
            "notification.course.name LIKE %:inputSearch%)")
    List<Notification> searchNotificationsInDatabase(@Param("username") String username, @Param("inputSearch") String inputSearch);
}
