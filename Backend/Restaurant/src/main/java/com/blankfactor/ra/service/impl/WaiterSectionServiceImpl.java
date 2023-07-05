package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.WaiterSectionDto;
import com.blankfactor.ra.model.Section;
import com.blankfactor.ra.model.WaiterSection;
import com.blankfactor.ra.repository.WaiterSectionRepository;
import com.blankfactor.ra.service.WaiterSectionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public String getWaitersFromSections(List<Section> sections) {
        List<WaiterSection> waiterSections = new ArrayList<>();

        for (Section section : sections) {
            waiterSections.addAll(waiterSectionRepository.findBySectionId(section.getId()));
        }

        if (waiterSections.isEmpty()) {
            throw new RuntimeException("No waiter section found");
        }

        return waiterSections.stream()
                .map(waiterSection -> String.valueOf(waiterSection.getWaiter().getId()))
                .collect(Collectors.joining(","));
    }
}