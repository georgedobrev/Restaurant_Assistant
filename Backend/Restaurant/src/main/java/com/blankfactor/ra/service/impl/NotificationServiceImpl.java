package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.NotificationDto;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Notification;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.repository.NotificationRepository;
import com.blankfactor.ra.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final AppTableRepository appTableRepository;

    @Override
    public Notification createNotification(NotificationDto notificationDto) {
        Notification notification = new Notification();

        notification.setAppTable(notificationDto.getAppTable());
        notification.setAppUser(notificationDto.getAppUser());
        notification.setRequestType(notificationDto.getRequestType());

        switch (notification.getRequestType()) {
            case Waiter ->
                    notification.setMessage("Table " + notificationDto.getAppTable().getTableNumber() + " requested a waiter.");
            case Bill ->
                    notification.setMessage("Table " + notificationDto.getAppTable().getTableNumber() + " requested the bill.");
            case Menu ->
                    notification.setMessage("Table " + notificationDto.getAppTable().getTableNumber() + " requested the menu.");
        }
        notification.setApproved(notificationDto.isApproved());
        notification.setCreatedAt(Instant.now()); //should be default

        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllNotificationsByRestaurantId(int restaurantId) {
        List<AppTable> appTables = appTableRepository.findByRestaurantId(restaurantId);
        List<Integer> appTableIds = new ArrayList<>();

        for (AppTable appTable : appTables) {
            appTableIds.add(appTable.getId());
        }

        return notificationRepository.findAllByAppTableIdIn(appTableIds);
    }

    @Override
    public List<Notification> getAllNotificationsByTableId(int tableId) {

        return notificationRepository.findAllByAppTableId(tableId);
    }

    @Override
    public Notification updateNotification(int notificationId) throws Exception {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(() -> new Exception("Notification" + notificationId + "not found."));
        notification.setApproved(!notification.getApproved());

        return notificationRepository.save(notification);
    }

    @Override
    public void deleteById(int notificationId) {
        notificationRepository.deleteNotificationById(notificationId);
    }

    @Override
    public void deleteAllNotificationsByTableId(int tableId) {
        notificationRepository.deleteAllByAppTableId(tableId);
    }
}
