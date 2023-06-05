package com.blankfactor.ra.dto;

import com.blankfactor.ra.model.AppTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationsDto {

    private int id;
    private AppTable appTable;
    private String requestType;
    private LocalDate time;
    private String message;
    private boolean approved;


}
