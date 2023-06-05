package com.blankfactor.ra.dto;

import com.blankfactor.ra.model.AppTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {

    private AppTable appTable;
    private String requestType;
    private String message;
    private boolean approved;


}
