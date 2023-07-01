package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.WaiterSectionDto;
import com.blankfactor.ra.model.Section;
import com.blankfactor.ra.model.WaiterSection;

import java.util.List;

public interface WaiterSectionService {
    WaiterSection createWaiterSection(WaiterSectionDto waiterSectionDto);

    String getWaitersFromSections(List<Section> sections);
}
