package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.NotificationDto;
import com.blankfactor.ra.exceptions.TableException;
import com.blankfactor.ra.exceptions.UserException;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Notification;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.repository.NotificationRepository;
import com.blankfactor.ra.repository.UserRepository;
import com.blankfactor.ra.service.NotificationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final AppTableRepository appTableRepository;
    private final ModelMapper modelMapper;

    @Override
    public Notification createNotification(NotificationDto notificationDto) {
//        AppUser appUser = userRepository
//                .findById(notificationDto.getAppUser().getId())
//                .orElseThrow(() -> new UserException("User not found"));
//
//        AppTable appTable = appTableRepository.findById(notificationDto.getAppTable().getId())
//                .orElseThrow(() -> new TableException("Table not found"));

//        Notification notification = modelMapper.map(notificationDto, Notification.class);
        Notification notification = new Notification();
        notification.setAppUser(notificationDto.getAppUser());
        notification.setAppTable(notificationDto.getAppTable());
        notification.setRequestType(notificationDto.getRequestType());
        notification.setMessage(notificationDto.getMessage());
        notification.setApproved(notificationDto.getApproved());

        return notificationRepository.save(notification);
    }
}
