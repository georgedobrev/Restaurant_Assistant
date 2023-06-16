package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.SectionDto;
import com.blankfactor.ra.exceptions.custom.SectionDuplicateException;
import com.blankfactor.ra.model.*;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.repository.SectionRepository;
import com.blankfactor.ra.service.RestaurantService;
import com.blankfactor.ra.service.SectionService;
import com.blankfactor.ra.service.UserService;
import com.blankfactor.ra.service.WaiterSectionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final RestaurantService restaurantService;
    private final UserService userService;
    private final AppTableRepository appTableRepository;
    private final WaiterSectionService waiterSectionService;

    @Transactional
    @Override
    public Section createSection(SectionDto sectionDto) {
        Restaurant restaurant = sectionDto.getAppTables().get(0).getRestaurant();

        Section section = Section.builder()
                .sectionName(sectionDto.getSectionName())
                .restaurant(restaurant)
                .build();

        return sectionRepository.save(section);
    }

    private void assignSectionToTables(List<AppTable> tables, Section section) {
        for (AppTable table : tables) {
            if (table.getSection() == null) {
                table.setSection(section);
                appTableRepository.save(table);
            } else {
                throw new SectionDuplicateException("Table " + table.getTableNumber() + " is already assigned to a section!");
            }
        }
    }

    private String convertFromIntListToString(List<AppTable> tables) {
        return tables.stream()
                .map(table -> Integer.toString(table.getTableNumber()))
                .collect(Collectors.joining(","));
    }

    @Override
    public List<Section> getAllSections(Integer restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        List<Section> allSections = sectionRepository.findByRestaurant(restaurant);

        return allSections;
    }
}
