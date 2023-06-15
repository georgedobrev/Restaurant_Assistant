package com.blankfactor.ra.service;

import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Section;

public interface WaiterSectionService {
    void createWaiterSection(Section section, AppUser waiter);
}
