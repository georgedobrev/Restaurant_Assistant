package com.blankfactor.ra.dto;

import com.blankfactor.ra.enums.RequestType;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class NotificationDto {

    private AppTable appTable;
    private AppUser appUser;
    private RequestType requestType;
    private String message;
    private boolean approved;
    private Instant createdAt;
}
