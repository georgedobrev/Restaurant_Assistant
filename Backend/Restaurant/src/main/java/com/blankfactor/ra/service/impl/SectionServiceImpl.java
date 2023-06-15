package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.SectionDto;
import com.blankfactor.ra.exceptions.custom.SectionDuplicateException;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.Section;
import com.blankfactor.ra.repository.SectionRepository;
import com.blankfactor.ra.repository.UserRepository;
import com.blankfactor.ra.service.AppTableService;
import com.blankfactor.ra.service.RestaurantService;
import com.blankfactor.ra.service.SectionService;
import com.blankfactor.ra.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final RestaurantService restaurantService;
    private final UserRepository userRepository;
    private final AppTableService appTableService;
    private final UserService userService;

    @Override
    public Section createSection(Integer restaurantId, SectionDto sectionDto) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        AppUser waiter = userService.getUserByEmail(sectionDto.getWaiterEmail());

        Section section = Section.builder()
                .sectionNumber(sectionDto.getSectionNumber())
                .restaurant(restaurant)
                .waiter(waiter)
                .build();

        assignSectionToTables(restaurantId, sectionDto.getTableNumbers(), section);
        String tableNumbers = convertFromIntListToString(sectionDto.getTableNumbers());
        section.setTableNumbers(tableNumbers);

        return sectionRepository.save(section);
    }

    private void assignSectionToTables(Integer restaurantId, List<Integer> tableNumbers, Section section) {
        for (int tableNumber : tableNumbers) {
            AppTable table = appTableService.getTableByRestaurantIdAndTableNumber(tableNumber, restaurantId);

            if (table.getSection() == null) {
                table.setSection(section);
            } else {
                throw new SectionDuplicateException("Table " + table.getTableNumber() + " is already assigned to a section");
            }
        }
    }

    private String convertFromIntListToString(List<Integer> listToConvert) {
        //TODO try with functional
        String tableNumbers = listToConvert.toString().replace(" ", "");

        return tableNumbers.substring(1, tableNumbers.length() - 1);
    }

    @Override
    public List<Section> getAllSections(Integer restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        List<Section> allSections = sectionRepository.findByRestaurant(restaurant);

        return allSections;
    }


}
