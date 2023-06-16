package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.ShiftDto;
import com.blankfactor.ra.enums.DayType;
import com.blankfactor.ra.model.Section;
import com.blankfactor.ra.model.Shift;
import com.blankfactor.ra.repository.ShiftRepository;
import com.blankfactor.ra.service.ShiftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ShiftServiceImpl implements ShiftService {
    private final ShiftRepository shiftRepository;

    @Override
    public Shift createShift(ShiftDto shiftDto) {
        Shift createdShift = Shift.builder()
                .startTime(shiftDto.getStartTime())
                .endTime(shiftDto.getEndTime())
                .dayFrom(DayType.valueOf(shiftDto.getDayFrom()))
                .dayTo(DayType.valueOf(shiftDto.getDayTo()))
                .build();

        return shiftRepository.save(createdShift);
    }

    @Override
    public List<Shift> getAllShifts(Integer restaurantId) {
        List<Shift> shifts = shiftRepository.findByRestaurantId(restaurantId);
        return shifts;
    }
}
