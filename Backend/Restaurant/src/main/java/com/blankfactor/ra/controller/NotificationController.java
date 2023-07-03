package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.NotificationDto;
import com.blankfactor.ra.model.Notification;
import com.blankfactor.ra.service.NotificationService;
import jakarta.validation.Valid;
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

    @PostMapping("/create")
    public ResponseEntity<Notification> createNotification(@Valid @RequestBody NotificationDto notificationDto) {
        var notification = notificationService.createNotification(notificationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(notification);
    }

    @GetMapping("/all/restaurant/{restaurant_id}")
    public ResponseEntity<List<Notification>> getAllNotificationsByRestaurantId(@PathVariable("restaurant_id") int restaurantId) {
        var notifications = notificationService.getAllNotificationsByRestaurantId(restaurantId);

        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/all/table/{table_id}")
    public ResponseEntity<List<Notification>> getAllNotificationsByTableId(@PathVariable("table_id") int tableId) {
        var notifications = notificationService.getAllNotificationsByTableId(tableId);

        return ResponseEntity.ok(notifications);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable("id") int notificationId) throws Exception {
        var notification = notificationService.updateNotification(notificationId);

        return ResponseEntity.ok(notification);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNotificationById(@PathVariable("id") int notificationId) {
        notificationService.deleteById(notificationId);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/all/{app_table_id}")
    public ResponseEntity<?> deleteAllNotifications(@PathVariable("app_table_id") int tableId) {
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
