package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.model.*;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.repository.MergedTableRepository;
import com.blankfactor.ra.repository.UserTableRepository;
import com.blankfactor.ra.service.MergedTableService;
import com.blankfactor.ra.service.SectionService;
import com.blankfactor.ra.service.UserTableService;
import com.blankfactor.ra.service.WaiterSectionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Data
public class UserTableServiceImpl implements UserTableService {
    private final MergedTableRepository mergedTableRepository;
    private final UserTableRepository userTableRepository;
    private final AppTableRepository appTableRepository;
    private final WaiterSectionService waiterSectionService;
    private final SectionService sectionService;
    private final MergedTableService mergedTableService;

    @Override
    public void createUserTable(AppTable appTable, AppUser user) {
        List<Section> sections = sectionService.getSectionsForTable(appTable);
        String waiterIds = waiterSectionService.getWaitersFromSections(sections);


        if (appTable.isMergedTable()) {
            MergedTable mergedTable = mergedTableService.getMergedTableByAppTableNumber(appTable.getRestaurant().getId(), appTable.getTableNumber());

            if (!isAppUserSeated(user, mergedTable)) {
                createUserTableRecord(user, waiterIds, null, mergedTable);
            }

        } else {

            if (!isAppUserSeated(user, appTable)) {
                createUserTableRecord(user, waiterIds, appTable, null);
            }
        }
    }

    public void createUserTableRecord(AppUser user, String waiterIds, AppTable appTable, MergedTable mergedTable) {
        UserTable userTable = UserTable.builder()
                .appUser(user)
                .waiterIds(waiterIds)
                .appTable(appTable)
                .mergedTable(mergedTable)
                .startTime(new Date().toInstant())
                .build();

        if (appTable != null) {
            appTable.setOccupied(true);
            appTableRepository.save(appTable);
        } else {
            mergedTable.setOccupied(true);
            mergedTableRepository.save(mergedTable);
        }

        userTableRepository.save(userTable);
    }

    public boolean isAppUserSeated(AppUser user, AppTable appTable) {
        UserTable userTable = userTableRepository.findByAppUserAndAppTableAndEndTimeIsNull(user, appTable).orElse(null);
        return userTable != null;
    }

    public boolean isAppUserSeated(AppUser user, MergedTable mergedTable) {
        UserTable userTable = userTableRepository.findByAppUserAndMergedTableAndEndTimeIsNull(user, mergedTable).orElse(null);
        return userTable != null;
    }
}