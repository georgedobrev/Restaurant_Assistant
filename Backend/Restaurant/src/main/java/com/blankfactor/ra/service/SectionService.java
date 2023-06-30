package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.SectionDto;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Section;

import java.util.List;

public interface SectionService {
    Section createSection(SectionDto sectionDto);

    List<Section> getAllSections(Integer restaurantId);

    List<Section> getSectionsForTable(AppTable table);

    Section updateSectionById(Integer sectionId, SectionDto sectionDto);

    void deleteSectionById(Integer sectionId);
}
