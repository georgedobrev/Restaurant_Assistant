package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.exceptions.custom.RestaurantException;
import com.blankfactor.ra.exceptions.custom.VirtualTableException;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.VirtualTable;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.repository.RestaurantRepository;
import com.blankfactor.ra.repository.VirtualTableRepository;
import com.blankfactor.ra.service.AppTableService;
import com.blankfactor.ra.service.VirtualTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class VirtualTableServiceImpl implements VirtualTableService {
    private final AppTableService appTableService;
    private final AppTableRepository appTableRepository;
    private final VirtualTableRepository virtualTableRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public VirtualTable createVirtualTable(Integer restaurantId, VirtualTable virtualTable) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException("Restaurant with id " + restaurantId + " not found"));

        setOccupiedForEachTableInVirtualTable(restaurantId, virtualTable, true);

        virtualTable.setRestaurant(restaurant);
        return virtualTableRepository.save(virtualTable);
    }

    @Override
    public VirtualTable getVirtualTableByTableIdsAndRestaurantId(String tableIds, Integer restaurantId) {
        return virtualTableRepository.findByTablesIdsAndRestaurantId(tableIds, restaurantId)
                .orElseThrow(() -> new VirtualTableException("Virtual table not found"));
    }

    @Override
    public VirtualTable updateVirtualTableByTableNumbers(Integer restaurantId, String tableNumbers, VirtualTable updatedVirtualTable) {
        VirtualTable existingVirtual = getVirtualTableByTableIdsAndRestaurantId(tableNumbers, restaurantId);
        existingVirtual.setTablesIds(updatedVirtualTable.getTablesIds());
        virtualTableRepository.save(existingVirtual);
        return existingVirtual;
    }

    @Override
    public Map<Integer, VirtualTable> getAllVirtualTablesByRestaurantId(Integer restaurantId) {
        return virtualTableRepository.findByRestaurantId(restaurantId)
                .stream()
                .collect(Collectors.toMap(VirtualTable::getId, Function.identity()));
    }

    @Override
    public void deleteVirtualTable(Integer restaurantId, VirtualTable virtualTable) {
        VirtualTable existingVirtualTable = getVirtualTableByTableIdsAndRestaurantId(virtualTable.getTablesIds(), restaurantId);

        setOccupiedForEachTableInVirtualTable(restaurantId, virtualTable, false);

        virtualTableRepository.delete(existingVirtualTable);
    }

    private void setOccupiedForEachTableInVirtualTable(Integer restaurantId, VirtualTable virtualTable, boolean isOccupied) {
        for (char c : virtualTable.getTablesIds().toCharArray()) {
            int tableNum = Character.getNumericValue(c);

            AppTable appTable = appTableService.getTableByTableNumber(restaurantId, tableNum);

            appTable.setVirtualTable(isOccupied);
            appTableRepository.save(appTable);
        }
    }
}