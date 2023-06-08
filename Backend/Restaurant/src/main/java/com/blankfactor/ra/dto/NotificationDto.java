package com.blankfactor.ra.dto;

import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private AppUser appUser;
    private AppTable appTable;
    private String requestType;
    private String message;
    private Boolean approved;
}
