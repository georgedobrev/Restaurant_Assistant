package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.ShiftDto;
import com.blankfactor.ra.enums.DayType;
import com.blankfactor.ra.exceptions.custom.ShiftException;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.Shift;
import com.blankfactor.ra.repository.ShiftRepository;
import com.blankfactor.ra.service.RestaurantService;
import com.blankfactor.ra.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftServiceImpl implements ShiftService {
    private final ShiftRepository shiftRepository;
    private final RestaurantService restaurantService;

    @Override
    public Shift createShift(Integer restaurantId, ShiftDto shiftDto) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        Shift createdShift = Shift.builder()
                .restaurant(restaurant)
                .startTime(shiftDto.getStartTime())
                .endTime(shiftDto.getEndTime())
                .dayFrom(DayType.valueOf(shiftDto.getDayFrom()))
                .dayTo(DayType.valueOf(shiftDto.getDayTo()))
                .build();

        return shiftRepository.save(createdShift);
    }

    @Override
    public List<Shift> getAllShiftsByRestaurantId(Integer restaurantId) {
        return shiftRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public Shift updateShiftById(Integer shiftId, ShiftDto shiftDto) throws ShiftException {
        Shift existingShift = shiftRepository.findById(shiftId).orElseThrow(() -> new ShiftException("Shift not found"));

        existingShift.setStartTime(shiftDto.getStartTime());
        existingShift.setEndTime(shiftDto.getEndTime());
        existingShift.setDayFrom(DayType.valueOf(shiftDto.getDayFrom()));
        existingShift.setDayTo(DayType.valueOf(shiftDto.getDayTo()));

        return shiftRepository.save(existingShift);
    }

    @Override
    public void deleteShiftById(Integer shiftId) throws ShiftException {
        Shift shift = shiftRepository.findById(shiftId).orElseThrow(() -> new ShiftException("Shift not found"));
        shiftRepository.delete(shift);
    }
}