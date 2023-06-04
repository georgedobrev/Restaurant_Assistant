package com.blankfactor.ra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationsDto {

    private int notification_id;
    private int table_id;
    private LocalDate time;
    private String message;
    private boolean approved;

}
