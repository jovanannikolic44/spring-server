package com.masterprojekat.springserver.services;

import com.masterprojekat.springserver.model.Notification;
import com.masterprojekat.springserver.model.User;
import com.masterprojekat.springserver.repository.NotificationRepository;
import com.masterprojekat.springserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications(String username) {
        return notificationRepository.findByStudentUsernameOrderByCreatedAtDesc(username);
    }
}
