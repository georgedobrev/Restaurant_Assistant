package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.WaiterSectionDto;
import com.blankfactor.ra.model.WaiterSection;
import com.blankfactor.ra.repository.WaiterSectionRepository;
import com.blankfactor.ra.service.WaiterSectionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class WaiterSectionServiceImpl implements WaiterSectionService {
    private final WaiterSectionRepository waiterSectionRepository;

    @Override
    public WaiterSection createWaiterSection(WaiterSectionDto waiterSectionDto) {
        WaiterSection createdWaiterSection = WaiterSection.builder()
                .section(waiterSectionDto.getSection())
                .waiter(waiterSectionDto.getWaiter())
                .shift(waiterSectionDto.getShift())
                .build();

        return waiterSectionRepository.save(createdWaiterSection);
    }
}
