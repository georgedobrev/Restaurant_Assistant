package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.NotificationsDto;
import com.blankfactor.ra.model.Notification;

public interface NotificationsService {
    Notification save(NotificationsDto notificationsDto);


}
