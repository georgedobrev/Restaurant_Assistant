package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.ShiftDto;
import com.blankfactor.ra.exceptions.custom.ShiftException;
import com.blankfactor.ra.model.Shift;

import java.util.List;

public interface ShiftService {
    Shift createShift(Integer restaurantId, ShiftDto shiftDto);

    List<Shift> getAllShiftsByRestaurantId(Integer restaurantId);

    Shift updateShiftById(Integer shiftId, ShiftDto shiftDto);

    void deleteShiftById(Integer shiftId);
}