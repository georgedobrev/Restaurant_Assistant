package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.VirtualTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VirtualTableRepository extends JpaRepository<VirtualTable, Integer> {
    List<VirtualTable> findByRestaurantId(Integer restaurantId);
}
