package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.ShiftDto;
import com.blankfactor.ra.enums.DayType;
import com.blankfactor.ra.exceptions.custom.ShiftException;
import com.blankfactor.ra.model.Section;
import com.blankfactor.ra.model.Shift;
import com.blankfactor.ra.repository.ShiftRepository;
import com.blankfactor.ra.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ShiftServiceImpl implements ShiftService {
    private final ShiftRepository shiftRepository;

    @Override
    public Shift createShift(ShiftDto shiftDto) {
        Shift createdShift = Shift.builder()
                .restaurant(shiftDto.getRestaurant())
                .startTime(shiftDto.getStartTime())
                .endTime(shiftDto.getEndTime())
                .dayFrom(DayType.valueOf(shiftDto.getDayFrom()))
                .dayTo(DayType.valueOf(shiftDto.getDayTo()))
                .build();

        return shiftRepository.save(createdShift);
    }

    @Override
    public List<Shift> getAllShifts(Integer restaurantId) {
        return shiftRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public Shift updateShift(Integer shiftId, ShiftDto shiftDto) throws ShiftException {
        Shift existingShift = shiftRepository.findById(shiftId).orElseThrow(() -> new ShiftException("Shift not found"));

        existingShift.setStartTime(shiftDto.getStartTime());
        existingShift.setEndTime(shiftDto.getEndTime());
        existingShift.setDayFrom(DayType.valueOf(shiftDto.getDayFrom()));
        existingShift.setDayTo(DayType.valueOf(shiftDto.getDayTo()));

        return shiftRepository.save(existingShift);
    }

    @Override
    public void deleteShift(Integer shiftId) throws ShiftException {
        Shift shift = shiftRepository.findById(shiftId).orElseThrow(() -> new ShiftException("Shift not found"));
        shiftRepository.delete(shift);
    }


}
