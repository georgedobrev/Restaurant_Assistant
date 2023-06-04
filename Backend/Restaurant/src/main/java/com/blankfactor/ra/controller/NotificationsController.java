package com.blankfactor.ra.controller;

import com.blankfactor.ra.model.Notification;
import com.blankfactor.ra.repository.NotificationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public class NotificationsController {

    private final NotificationRepository notificationRepository;

    public NotificationsController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @GetMapping("/notification/{id}")
    public Optional<Notification> findById(@RequestParam int id)
    {

        return notificationRepository.findById(id);
    }
}
