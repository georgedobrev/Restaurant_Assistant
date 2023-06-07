package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.NotificationDto;
import com.blankfactor.ra.model.Notification;
import com.blankfactor.ra.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/create")
    public ResponseEntity<Notification> createNotification(@RequestBody NotificationDto notificationDto)
    {
        Notification notification = notificationService.createNotification(notificationDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(notification);
    }

    @GetMapping("/all/restaurant/{id}")
    public List<ResponseEntity<Notification>> getAllNotificationsByRestaurantId(PathVariable(""))
}
