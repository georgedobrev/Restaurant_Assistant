package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.NotificationDto;
import com.blankfactor.ra.model.Notification;
import com.blankfactor.ra.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/notifications")
@RestController
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping()
    @Operation(summary = "Create notification")
    public ResponseEntity<Notification> createNotification(@RequestBody NotificationDto notificationDto) {
        var notification = notificationService.createNotification(notificationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(notification);
    }

    @GetMapping("/restaurants")
    @Operation(summary = "Get all notifications for a specific restaurant")
    public ResponseEntity<List<Notification>> getAllNotificationsByRestaurantId(@RequestParam("restaurant_Id") int restaurantId) {
        var notifications = notificationService.getAllNotificationsByRestaurantId(restaurantId);

        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/tables")
    @Operation(summary = "Get all notifications for a specific table")
    public ResponseEntity<List<Notification>> getAllNotificationsByTableId(@RequestParam("table_Id") int tableId) {
        var notifications = notificationService.getAllNotificationsByTableId(tableId);

        return ResponseEntity.ok(notifications);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update notification by notificationId")
    public ResponseEntity<Notification> updateNotification(@PathVariable("id") int notificationId) throws Exception {
        var notification = notificationService.updateNotification(notificationId);

        return ResponseEntity.ok(notification);
    }

    @DeleteMapping("/all/{appTableId}")
    @Operation(summary = "Delete all notifications by given table id")
    public ResponseEntity<?> deleteAllNotifications(@PathVariable("appTableId") int tableId) {
        notificationService.deleteAllNotificationsByTableId(tableId);

        return ResponseEntity.ok().build();
    }

    // TODO: Add websocket direction for user
    @MessageMapping("/app/notify")
    @SendTo("/topic/notifications")
    public Notification sendNotification(NotificationDto notificationDto) {
        return notificationService.createNotification(notificationDto);
    }
}