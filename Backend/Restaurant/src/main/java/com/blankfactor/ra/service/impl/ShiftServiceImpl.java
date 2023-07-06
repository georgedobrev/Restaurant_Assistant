package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.ShiftDto;
import com.blankfactor.ra.enums.DayType;
import com.blankfactor.ra.exceptions.custom.ShiftException;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.Shift;
import com.blankfactor.ra.repository.ShiftRepository;
import com.blankfactor.ra.service.RestaurantService;
import com.blankfactor.ra.service.ShiftService;
import jakarta.transaction.Transactional;
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
        Shift exsitingShift = shiftRepository.findByRestaurantIdAndShiftNameAndDeletedIsTrue(restaurantId, shiftDto.getShiftName()).orElse(null);

        Shift shift;
        if (exsitingShift == null) {
            shift = Shift.builder()
                    .restaurant(restaurant)
                    .shiftName(shiftDto.getShiftName())
                    .startTime(shiftDto.getStartTime())
                    .endTime(shiftDto.getEndTime())
                    .dayFrom(DayType.valueOf(shiftDto.getDayFrom()))
                    .dayTo(DayType.valueOf(shiftDto.getDayTo()))
                    .build();

            return shiftRepository.save(shift);
        }

        exsitingShift.setShiftName(shiftDto.getShiftName());
        exsitingShift.setStartTime(shiftDto.getStartTime());
        exsitingShift.setEndTime(shiftDto.getEndTime());
        exsitingShift.setDayFrom(DayType.valueOf(shiftDto.getDayFrom()));
        exsitingShift.setDayTo(DayType.valueOf(shiftDto.getDayTo()));
        exsitingShift.setDeleted(false);

        return shiftRepository.save(exsitingShift);
    }

    @Override
    public List<Shift> getAllShiftsByRestaurantId(Integer restaurantId) {
        return shiftRepository.findByRestaurantIdAndDeletedIsFalse(restaurantId);
    }

    @Override
    public Shift updateShiftById(Integer shiftId, ShiftDto shiftDto) {
        Shift existingShift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new ShiftException("Shift not found"));

        existingShift.setStartTime(shiftDto.getStartTime());
        existingShift.setEndTime(shiftDto.getEndTime());
        existingShift.setDayFrom(DayType.valueOf(shiftDto.getDayFrom()));
        existingShift.setDayTo(DayType.valueOf(shiftDto.getDayTo()));

        return shiftRepository.save(existingShift);
    }

    @Transactional
    @Override
    public void deleteShiftById(Integer shiftId) {
        Shift shift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new ShiftException("Shift not found"));
        shiftRepository.softDeleteShift(shiftId);
    }
}
