package com.blankfactor.ra.service.impl;

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
    public void createWaiterSection(Section section, AppUser waiter) {
        WaiterSection waiterSection = WaiterSection.builder()
                .section(section)
                .waiter(waiter)
                .build();

        waiterSectionRepository.save(waiterSection);
    }
}
