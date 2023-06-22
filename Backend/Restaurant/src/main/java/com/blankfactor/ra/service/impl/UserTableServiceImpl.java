package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.model.*;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.repository.SectionRepository;
import com.blankfactor.ra.repository.UserTableRepository;
import com.blankfactor.ra.repository.WaiterSectionRepository;
import com.blankfactor.ra.service.UserTableService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserTableServiceImpl implements UserTableService {
    private final UserTableRepository userTableRepository;
    private final SectionRepository sectionRepository;
    private final WaiterSectionRepository waiterSectionRepository;
    private final AppTableRepository appTableRepository;

    @Override
    public void createUserTable(AppTable appTable, AppUser user) {
        List<Section> sections = getSectionsForTable(appTable);
        String waiterIds = getWaitersFromSections(sections);

        boolean isSeated = isAppUserSeated(user, appTable);

        if (!isSeated) {
            UserTable userTable = UserTable.builder()
                    .appUser(user)
                    .waiterIds(waiterIds)
                    .appTable(appTable)
                    .startTime(new Date().toInstant())
                    .build();

            userTableRepository.save(userTable);

            appTable.setOccupied(true);
            appTableRepository.save(appTable);
        }
    }

    @Override
    public void setEndTimeForATable(AppTable appTable) {
        List<UserTable> userTables = userTableRepository.findByAppTableIdAndEndTimeIsNull(appTable.getId());
        userTables.forEach(userTable -> {
            userTable.setEndTime(new Date().toInstant());
            userTableRepository.save(userTable);
        });
    }

    public boolean isAppUserSeated(AppUser user, AppTable appTable) {
        UserTable userTable = userTableRepository.findByAppUserIdAndAppTableId(user.getId(), appTable).orElse(null);
        if (userTable == null) {
            return false;
        }
        return userTable.getEndTime() == null;
    }

    private List<Section> getSectionsForTable(AppTable table) {
        List<Section> sections = sectionRepository.findByRestaurant(table.getRestaurant());
        int currentTableNum = table.getTableNumber();

        return sections.stream()
                .filter(section -> Arrays.stream(section.getTableNumbers().split(","))
                        .map(Integer::valueOf)
                        .anyMatch(num -> num == currentTableNum))
                .collect(Collectors.toList());
    }

    public String getWaitersFromSections(List<Section> sections) {
        List<WaiterSection> waiterSections = new ArrayList<>();

        for (Section section : sections) {
            waiterSections.addAll(waiterSectionRepository.findBySectionId(section.getId()));
        }

        if (waiterSections.isEmpty()) {
            throw new RuntimeException("No waiter section found");
        }

        return waiterSections.stream()
                .map(waiterSection -> String.valueOf(waiterSection.getWaiter().getId()))
                .collect(Collectors.joining(","));
    }
}
