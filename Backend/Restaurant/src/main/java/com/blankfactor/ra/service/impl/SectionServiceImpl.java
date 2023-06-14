package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.SectionDto;
import com.blankfactor.ra.exceptions.custom.AppTableException;
import com.blankfactor.ra.exceptions.custom.SectionDuplicateException;
import com.blankfactor.ra.exceptions.custom.UserException;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.Section;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.repository.SectionRepository;
import com.blankfactor.ra.repository.UserRepository;
import com.blankfactor.ra.service.RestaurantService;
import com.blankfactor.ra.service.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final RestaurantService restaurantService;
    private final UserRepository userRepository;
    private final AppTableRepository appTableRepository;

    @Override
    public Section createSection(Integer restaurantId, SectionDto sectionDto) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        AppUser waiter = userRepository.findAppUserByEmail(sectionDto.getWaiterUsername())
                .orElseThrow(() -> new UserException("User " + sectionDto.getWaiterUsername() + " not found"));

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
            AppTable table = appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber)
                    .orElseThrow(() -> new AppTableException("Table " + tableNumber + " not found"));

            if (table.getSection() == null) {
                table.setSection(section);
            } else {
                throw new SectionDuplicateException("Table " + table.getTableNumber() + " is already assigned to a section");
            }
        }
    }

    private String convertFromIntListToString(List<Integer> listToConvert) {
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
