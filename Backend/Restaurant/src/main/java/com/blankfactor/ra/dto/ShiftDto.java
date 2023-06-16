package com.blankfactor.ra.dto;

import com.blankfactor.ra.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiftDto {

    private Restaurant restaurant;
    private LocalTime startTime;
    private LocalTime endTime;
    private String dayFrom;
    private String dayTo;
}
