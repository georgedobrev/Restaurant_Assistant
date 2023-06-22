package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.VirtualTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VirtualTableRepository extends JpaRepository<VirtualTable, Integer> {
    Optional<VirtualTable> findByTableNumbersAndRestaurantId(String tableIds, Integer restaurantId);

    List<VirtualTable> findByRestaurantId(Integer restaurantId);
}
