package com.blankfactor.ra.repository;


import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppTableRepository extends JpaRepository<AppTable, Integer> {
    List<AppTable> findByRestaurantId(Integer restaurantId);

    Optional<AppTable> findByRestaurantIdAndTableNumber(Integer restaurantId, Integer tableNumber);

    Optional<AppTable> findByQrId(Integer qrId);

    List<AppTable> findByRestaurantIdAndTableNumberIn(Integer restaurantId, List<Integer> tableNumbers);
}