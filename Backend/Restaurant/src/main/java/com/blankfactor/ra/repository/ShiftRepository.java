package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {
    List<Shift> findByRestaurantId(Integer restaurantId);
    @Modifying
    @Query("UPDATE Shift s SET s.deleted = true WHERE s.id = :shiftId")
    void softDeleteShift(Integer shiftId);
}
