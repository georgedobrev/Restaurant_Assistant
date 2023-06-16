package com.blankfactor.ra.dto;

import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Section;
import com.blankfactor.ra.model.Shift;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WaiterSectionDto {
    private Section section;
    private AppUser waiter;
    private Shift shift;
}
