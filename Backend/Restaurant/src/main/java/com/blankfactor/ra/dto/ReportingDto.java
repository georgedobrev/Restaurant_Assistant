package com.blankfactor.ra.dto;

import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Restaurant;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportingDto {
    @NotNull(message = "Table cannot be null")
    private AppTable table;

    @NotNull(message = "Restaurant cannot be null")
    private Restaurant restaurant;

    @NotNull(message = "Report from cannot be null")
    private AppUser reportFrom;

    @NotNull(message = "Report to cannot be null")
    private AppUser reportTo;
}
