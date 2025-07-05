package com.masterprojekat.springserver.controller;

import com.masterprojekat.springserver.model.Notification;
import com.masterprojekat.springserver.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping("/notification/get-all-for-student")
    public ResponseEntity<List<Notification>> getAllNotificationsForStudent(@RequestParam String student_username) {
        List<Notification> allNotifications = notificationService.getAllNotifications(student_username);
        if (allNotifications != null) {
            return ResponseEntity.ok(allNotifications);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/notification/create-new-notification")
    public ResponseEntity<Void> createNewNotification(@RequestParam int termId, @RequestParam String acceptOrRejectMessage) {
        notificationService.createNewNotification(termId, acceptOrRejectMessage);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/notification/search")
    ResponseEntity<List<Notification>> searchNotifications(String username, String inputSearch) {
        List<Notification> foundNotifications = notificationService.searchNotifications(username, inputSearch);
        if (foundNotifications != null) {
            return ResponseEntity.ok(foundNotifications);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
