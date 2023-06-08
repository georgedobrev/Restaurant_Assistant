package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
