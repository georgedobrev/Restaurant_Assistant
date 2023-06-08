package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.NotificationDto;
import com.blankfactor.ra.model.Notification;
import com.blankfactor.ra.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/notification")
@RestController
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping()
    public ResponseEntity<Notification> createNotification(@RequestBody NotificationDto notificationDto) {
        Notification createdNotification = notificationService.createNotification(notificationDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdNotification);
    }

    @MessageMapping("/app/notify")
    @SendTo("/topic/notifications")
    public Notification sendNotification(NotificationDto notificationDto) throws InterruptedException {
        Thread.sleep(2000);

        return notificationService.createNotification(notificationDto);
    }
}
