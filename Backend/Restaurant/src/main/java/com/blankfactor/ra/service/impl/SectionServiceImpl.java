package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.SectionDto;
import com.blankfactor.ra.exceptions.custom.SectionException;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.Section;
import com.blankfactor.ra.repository.SectionRepository;
import com.blankfactor.ra.service.RestaurantService;
import com.blankfactor.ra.service.SectionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final RestaurantService restaurantService;

    @Transactional
    @Override
    public Section createSection(SectionDto sectionDto) {
        Restaurant restaurant = sectionDto.getAppTables().get(0).getRestaurant();
        String tableNumbers = mapTableNumbersToString(sectionDto.getAppTables());

        Section section = Section.builder()
                .sectionName(sectionDto.getSectionName())
                .restaurant(restaurant)
                .tableNumbers(tableNumbers)
                .build();

        return sectionRepository.save(section);

        // TODO research how to implement object mapper to create section
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//
//            Section section = mapper.convertValue(sectionDto, Section.class);
//
//            Restaurant restaurant = sectionDto.getAppTables().get(0).getRestaurant();
//            String tableNumbers = mapTableNumbersToString(sectionDto.getAppTables());
//
//            section.setRestaurant(restaurant);
//            section.setTableNumbers(tableNumbers);
//            return sectionRepository.save(section);
//        } catch (IllegalArgumentException e) {
//            throw new RuntimeException("Error mapping SectionDto to Section object");
//        }
    }

    private String mapTableNumbersToString(List<AppTable> tables) {
        return tables.stream()
                .map(table -> Integer.toString(table.getTableNumber()))
                .collect(Collectors.joining(","));
    }

    @Override
    public List<Section> getAllSections(Integer restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        List<Section> allSections = sectionRepository.findByRestaurant(restaurant);

        return allSections;
    }

    @Override
    public Section updateSectionById(Integer sectionId, SectionDto sectionDto) {
        Section existingSection = getSectionById(sectionId);

        String tableNumbers = mapTableNumbersToString(sectionDto.getAppTables());
        existingSection.setSectionName(sectionDto.getSectionName());
        existingSection.setTableNumbers(tableNumbers);

        return sectionRepository.save(existingSection);
    }

    @Override
    public void deleteSectionById(Integer sectionId) {
        Section section = getSectionById(sectionId);
        sectionRepository.deleteById(sectionId);
    }

    public Section getSectionById(Integer sectionId) {
        return sectionRepository.findById(sectionId).orElseThrow(() -> new SectionException("Section not found"));
    }

    @Override
    public List<Section> getSectionsForTable(AppTable table) {
        List<Section> sections = sectionRepository.findByRestaurant(table.getRestaurant());
        int currentTableNum = table.getTableNumber();

        // TODO check if we can make it with objectMapper
        return sections.stream()
                .filter(section -> Arrays.stream(section.getTableNumbers().split(","))
                        .map(Integer::valueOf)
                        .anyMatch(num -> num == currentTableNum))
                .collect(Collectors.toList());
    }
}