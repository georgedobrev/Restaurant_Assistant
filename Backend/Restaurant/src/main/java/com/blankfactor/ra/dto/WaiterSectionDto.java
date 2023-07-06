package com.blankfactor.ra.dto;

import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Section;
import com.blankfactor.ra.model.Shift;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WaiterSectionDto {
    @NotNull(message = "Section cannot be null")
    private Section section;

    @NotNull(message = "Waiter cannot be null")
    private AppUser waiter;

    @NotNull(message = "Shift cannot be null")
    private Shift shift;
}