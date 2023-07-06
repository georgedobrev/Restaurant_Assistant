package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.MergedTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MergedTableRepository extends JpaRepository<MergedTable, Integer> {
    List<MergedTable> findByRestaurantId(Integer restaurantId);
}