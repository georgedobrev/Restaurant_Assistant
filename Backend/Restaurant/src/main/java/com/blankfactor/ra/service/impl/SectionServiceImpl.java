package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.SectionDto;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.Section;
import com.blankfactor.ra.repository.SectionRepository;
import com.blankfactor.ra.service.RestaurantService;
import com.blankfactor.ra.service.SectionService;
import com.blankfactor.ra.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import java.util.List;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {
    private static final Logger log = LoggerFactory.getLogger(SectionServiceImpl.class);
    private final SectionRepository sectionRepository;
    private final RestaurantService restaurantService;
    private final UserService userService;

    @Override
    public Section createSection(SectionDto sectionDto) {
        AppUser waiter = userService.getUserByEmail(sectionDto.getWaiterEmail());
        Restaurant restaurant = sectionDto.getAppTables().get(0).getRestaurant();

        Section section = Section.builder()
                .sectionName(sectionDto.getSectionName())
                .restaurant(restaurant)
                .waiter(waiter)
                .build();


        assignSectionToTables(sectionDto.getAppTables(), section);
        String tableNumbers = convertFromIntListToString(sectionDto.getAppTables());
        section.setTableNumbers(tableNumbers);

        return sectionRepository.save(section);
    }

    private void assignSectionToTables(List<AppTable> tables, Section section) {
        for (AppTable table : tables) {
            if (table.getSection() == null) {
                table.setSection(section);
            } else {
                log.warn("Table " + table.getTableNumber() + " is already assigned to a section!");
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
