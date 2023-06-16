package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.WaiterSectionDto;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Section;
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
    public WaiterSection createWaiterSection(WaiterSectionDto waiterSection) {
        WaiterSection createdWaiterSection = WaiterSection.builder()
                .section(waiterSection.getSection())
                .waiter(waiterSection.getWaiter())
                .shift(waiterSection.getShift())
                .build();

        return waiterSectionRepository.save(createdWaiterSection);
    }
}
