package com.blankfactor.ra.dto;

import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
    private AppTable tableId;
    private Restaurant restaurantId;
    private AppUser reportFrom;
    private AppUser reportTo;
}
