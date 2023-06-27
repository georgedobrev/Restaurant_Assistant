package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.model.*;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.repository.UserTableRepository;
import com.blankfactor.ra.repository.VirtualTableRepository;
import com.blankfactor.ra.service.SectionService;
import com.blankfactor.ra.service.UserTableService;
import com.blankfactor.ra.service.VirtualTableService;
import com.blankfactor.ra.service.WaiterSectionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Data
public class UserTableServiceImpl implements UserTableService {
    private final VirtualTableRepository virtualTableRepository;
    private final UserTableRepository userTableRepository;
    private final AppTableRepository appTableRepository;
    private final WaiterSectionService waiterSectionService;
    private final SectionService sectionService;
    private final VirtualTableService virtualTableService;

    @Override
    public void createUserTable(AppTable appTable, AppUser user) {
        List<Section> sections = sectionService.getSectionsForTable(appTable);
        String waiterIds = waiterSectionService.getWaitersFromSections(sections);


//        if (!isAppUserSeated(user, appTable)) {
        if (appTable.isVirtualTable()) {
            VirtualTable virtualTable = virtualTableService.getVirtualTableByAppTableNumber(appTable.getRestaurant().getId(), appTable.getTableNumber());

            createUserTableRecord(user, waiterIds, virtualTable.getTableNumbers(), null, virtualTable);
        } else {
            createUserTableRecord(user, waiterIds, null, appTable, null);

            appTable.setOccupied(true);
            appTableRepository.save(appTable);
        }
    }
//    }

    public void createUserTableRecord(AppUser user, String waiterIds, String tableNumbers, AppTable appTable, VirtualTable virtualTable) {
        UserTable userTable = UserTable.builder()
                .appUser(user)
                .waiterIds(waiterIds)
                .appTable(appTable)
                .tableNumbers(tableNumbers)
                .startTime(new Date().toInstant())
                .build();

        if (appTable != null) {
            appTable.setOccupied(true);
            appTableRepository.save(appTable);
        } else {
            virtualTable.setOccupied(true);
            virtualTableRepository.save(virtualTable);
        }
        userTableRepository.save(userTable);
    }

    public boolean isAppUserSeated(AppUser user, AppTable appTable) {
        UserTable userTable = userTableRepository.findByAppUserAndAppTableAndEndTimeIsNull(user, appTable).orElse(null);
        if (userTable == null) {
            return false;
        }
        return userTable.getEndTime() == null;
    }

    public boolean isAppUserSeated(AppUser user, VirtualTable virtualTable) {
        UserTable userTable = userTableRepository.findByAppUserAndVirtualTableAndEndTimeIsNull(virtualTable);
        // TODO
        return true;
    }

//    private List<Section> getSectionsForTable(AppTable table) {
//        List<Section> sections = sectionRepository.findByRestaurant(table.getRestaurant());
//        int currentTableNum = table.getTableNumber();
//
//        return sections.stream()
//                .filter(section -> Arrays.stream(section.getTableNumbers().split(","))
//                        .map(Integer::valueOf)
//                        .anyMatch(num -> num == currentTableNum))
//                .collect(Collectors.toList());
//    }

//    public String getWaitersFromSections(List<Section> sections) {
//        List<WaiterSection> waiterSections = new ArrayList<>();
//
//        for (Section section : sections) {
//            waiterSections.addAll(waiterSectionRepository.findBySectionId(section.getId()));
//        }
//
//        if (waiterSections.isEmpty()) {
//            throw new RuntimeException("No waiter section found");
//        }
//
//        return waiterSections.stream()
//                .map(waiterSection -> String.valueOf(waiterSection.getWaiter().getId()))
//                .collect(Collectors.joining(","));
//    }
}
