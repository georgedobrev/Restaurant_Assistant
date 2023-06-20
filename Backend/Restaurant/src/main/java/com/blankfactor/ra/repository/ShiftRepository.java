package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {
    List<Shift> findByRestaurantId(Integer restaurantId);
}
