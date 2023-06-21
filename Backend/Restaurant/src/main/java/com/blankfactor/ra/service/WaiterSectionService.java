package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.WaiterSectionDto;
import com.blankfactor.ra.model.WaiterSection;

public interface WaiterSectionService {
    WaiterSection createWaiterSection(WaiterSectionDto waiterSectionDto);
}
