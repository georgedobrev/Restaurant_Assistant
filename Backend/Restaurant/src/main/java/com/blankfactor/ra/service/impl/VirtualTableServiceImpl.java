package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.exceptions.custom.AppTableException;
import com.blankfactor.ra.exceptions.custom.VirtualTableException;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.VirtualTable;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.repository.RestaurantRepository;
import com.blankfactor.ra.repository.VirtualTableRepository;
import com.blankfactor.ra.service.VirtualTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class VirtualTableServiceImpl implements VirtualTableService {
    private final AppTableRepository appTableRepository;
    private final VirtualTableRepository virtualTableRepository;
    private final RestaurantRepository restaurantRepository;
    @Override
    public VirtualTable createVirtualTable(Integer restaurantId, VirtualTable virtualTable) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResolutionException("Restaurant with id " + restaurantId + " not found"));

        for(char c : virtualTable.getTablesIds().toCharArray()) {
            int tableNum = Character.getNumericValue(c);

            AppTable appTable = appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNum)
                    .orElseThrow(() -> new AppTableException("Table with number " + tableNum + " not found"));

            appTable.setVirtualTable(true);
        }
        virtualTable.setRestaurant(restaurant);
        return virtualTableRepository.save(virtualTable);
    }

    @Override
    public VirtualTable getVirtualTableByTableIdsAndRestaurantId(String tableIds, Integer restaurantId) {
        return virtualTableRepository.findByTablesIdsAndRestaurantId(tableIds, restaurantId).orElseThrow(() -> new VirtualTableException("Virtual table not found"));
    }

    @Override
    public VirtualTable updateVirtualTableByTableNumbers(Integer restaurantId, String tableNumbers, VirtualTable updatedVirtualTable) {
        VirtualTable existingVirtual = virtualTableRepository.findByTablesIdsAndRestaurantId(tableNumbers, restaurantId).orElseThrow(() -> new VirtualTableException("Virtual table not found"));
        existingVirtual.setTablesIds(updatedVirtualTable.getTablesIds());
        virtualTableRepository.save(existingVirtual);
        return existingVirtual;
    }

    @Override
    public Map<Integer, VirtualTable> getAllVirtualTablesByRestaurantId(Integer restaurantId) {
        List<VirtualTable> virtualTables = virtualTableRepository.findByRestaurantId(restaurantId);

        Map<Integer, VirtualTable> mappedVirtualTables = new HashMap<>();

        for(VirtualTable table : virtualTables) {
            mappedVirtualTables.put(table.getId(), table);
        }
        return mappedVirtualTables;
    }
}



















