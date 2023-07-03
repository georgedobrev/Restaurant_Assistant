package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.NotificationDto;
import com.blankfactor.ra.model.Notification;
import com.blankfactor.ra.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/notification")
@RestController
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping()
    public ResponseEntity<Notification> createNotification(@RequestBody NotificationDto notificationDto) {
        var notification = notificationService.createNotification(notificationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(notification);
    }

    @GetMapping("/restaurants/{restaurantId}")
    public ResponseEntity<List<Notification>> getAllNotificationsByRestaurantId(@PathVariable("restaurantId") int restaurantId) {
        var notifications = notificationService.getAllNotificationsByRestaurantId(restaurantId);

        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/tables/{tableId}")
    public ResponseEntity<List<Notification>> getAllNotificationsByTableId(@PathVariable("tableId") int tableId) {
        var notifications = notificationService.getAllNotificationsByTableId(tableId);

        return ResponseEntity.ok(notifications);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable("id") int notificationId) throws Exception {
        var notification = notificationService.updateNotification(notificationId);

        return ResponseEntity.ok(notification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotificationById(@PathVariable("id") int notificationId) {
        notificationService.deleteById(notificationId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/all/{appTableId}")
    public ResponseEntity<?> deleteAllNotifications(@PathVariable("appTableId") int tableId) {
        notificationService.deleteAllNotificationsByTableId(tableId);

        return ResponseEntity.ok().build();
    }

    @MessageMapping("/app/notify")
    @SendTo("/topic/notifications")
    public Notification sendNotification(NotificationDto notificationDto) throws InterruptedException {
        Thread.sleep(2000);

        return notificationService.createNotification(notificationDto);
    }
}
