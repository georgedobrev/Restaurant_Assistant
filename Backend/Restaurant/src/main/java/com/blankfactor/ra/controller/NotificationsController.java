package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.NotificationsDto;
import com.blankfactor.ra.model.Notification;
import com.blankfactor.ra.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class NotificationsController {

    @Qualifier
    private final NotificationService notificationService;


    @PostMapping("/new-notification")
    public ResponseEntity<Notification> createNotification(@RequestBody NotificationsDto notificationsDto)
    {
        Notification createdNotification = notificationService.save(notificationsDto);

        return ResponseEntity.ok(createdNotification);
    }


    @GetMapping("/find-notification/{id}")
    public Optional<Notification> findNotificationById(@PathVariable("id") Integer notificationId)
    {
        //notificationService.getNotificationById(notificationId);

      // Optional<Notification> notificationsDtos = Optional.ofNullable(notificationService.getNotificationById(notificationId));
        return notificationService.findNotificationById(notificationId);
    }

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notificationsDtos = notificationService.getNotifications();
        return ResponseEntity.ok(notificationsDtos);
    }

    @DeleteMapping(path = "/notification/{id}")
    public void deleteNotification (@PathVariable("id") int notificationId)
    {
        notificationService.deleteNotification(notificationId);
    }

    @DeleteMapping(path = "/delete-notifications")
    public void deleteAllNotifications()
    {
        notificationService.deleteAllNotifications();
    }

//    @PutMapping(path = "{notificationId}")
//    public void updateStudent(@PathVariable("notificationId") int notificationId,
//                              @RequestParam(required = false) String message,
//                              @RequestParam(required = false) LocalDate time)
//    {
//        notificationService.updateNotification(notificationId, message, time);
//    }
}
