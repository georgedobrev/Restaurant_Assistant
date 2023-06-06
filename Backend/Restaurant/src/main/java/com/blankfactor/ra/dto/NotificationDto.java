package com.blankfactor.ra.dto;

import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {

    private AppTable appTable;
    private AppUser appUser;
    private String requestType;
    private String message;
    private boolean approved;


}


