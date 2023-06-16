package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.ShiftDto;
import com.blankfactor.ra.model.Section;
import com.blankfactor.ra.model.Shift;

import java.util.List;

public interface ShiftService {
    Shift createShift(ShiftDto shiftDto);

    List<Shift> getAllShifts(Integer restaurantId);
}
