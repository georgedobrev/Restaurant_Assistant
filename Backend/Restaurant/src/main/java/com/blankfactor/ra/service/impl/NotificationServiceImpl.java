package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.NotificationDto;
import com.blankfactor.ra.exceptions.custom.AppTableException;
import com.blankfactor.ra.model.*;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Notification;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.repository.NotificationRepository;
import com.blankfactor.ra.repository.SectionRepository;
import com.blankfactor.ra.repository.WaiterSectionRepository;
import com.blankfactor.ra.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final SimpMessagingTemplate template;
    private final NotificationRepository notificationRepository;
    private final AppTableRepository appTableRepository;
    private final SectionRepository sectionRepository;
    private final WaiterSectionRepository waiterSectionRepository;

    @Override
    public Notification createNotification(NotificationDto notificationDto) {
        AppTable appTable = appTableRepository.findById(notificationDto.getAppTable().getId())
                .orElseThrow(() -> new AppTableException("App table " + notificationDto.getAppTable().getId() + " not found"));

        String notificationMessage = switch (notificationDto.getRequestType()) {
            case Waiter -> "Table " + appTable.getTableNumber() + " requested a waiter.";
            case Bill -> "Table " + appTable.getTableNumber() + " requested the bill.";
            case Menu -> "Table " + appTable.getTableNumber() + " requested the menu.";
        };

        Notification notification = Notification.builder()
                .appUser(notificationDto.getAppUser())
                .appTable(appTable)
                .requestType(notificationDto.getRequestType())
                .message(notificationMessage)
                .build();

        sendNotificationToWaiter(notification);

        return notificationRepository.save(notification);
    }

    private void sendNotificationToWaiter(Notification notification) {
        List<WaiterSection> waiterSections = new ArrayList<>();

        String tableNumber = String.valueOf(notification.getAppTable().getTableNumber());
        List<Section> sectionsFromTable = sectionRepository.findAllByTableNumbersContains(tableNumber);

        for (Section section : sectionsFromTable) {
            waiterSections = waiterSectionRepository.findBySectionId(section.getId());
        }

        for (WaiterSection waiterSection : waiterSections) {
            template.convertAndSendToUser(waiterSection.getWaiter().getEmail(), "/topic/message", notification);
        }
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
        notificationRepository.deleteById(notificationId);
    }

    @Override
    public void deleteAllNotificationsByTableId(int tableId) {
        notificationRepository.deleteAllByAppTableId(tableId);
    }
}