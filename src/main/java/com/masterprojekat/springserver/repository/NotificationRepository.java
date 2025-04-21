package com.masterprojekat.springserver.repository;

import com.masterprojekat.springserver.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByStudentUsernameOrderByCreatedAtDesc(String username);
}
