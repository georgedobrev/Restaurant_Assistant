package com.blankfactor.ra.controller;

import com.blankfactor.ra.model.Notification;
import com.blankfactor.ra.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    @PutMapping("/update/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable("id") int notificationId) {
        Notification notification = notificationService.updateNotification(notificationId);

        return ResponseEntity.ok(notification);
    }
}
