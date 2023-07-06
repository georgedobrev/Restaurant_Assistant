package com.blankfactor.ra.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiftDto {

    @NotNull(message = "Shift name cannot be null")
    @Size(max = 50, message = "Shift name cannot be more than 50 characters")
    private String shiftName;

    @NotNull(message = "StartTime cannot be null")
    private LocalTime startTime;

    @NotNull(message = "EndTime cannot be null")
    private LocalTime endTime;

    @Pattern(regexp = "^(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday)$", message = "Invalid DayType for dayFrom")
    @NotNull(message = "DayFrom cannot be null")
    private String dayFrom;

    @Pattern(regexp = "^(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday)$", message = "Invalid DayType for dayTo")
    @NotNull(message = "DayTo cannot be null")
    private String dayTo;
}
