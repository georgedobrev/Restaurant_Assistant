package com.blankfactor.ra.repository;


import com.blankfactor.ra.model.AppTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppTableRepository extends JpaRepository<AppTable, Integer> {
    List<AppTable> findByRestaurantIdAndDeletedIsFalse(Integer restaurantId);

    Optional<AppTable> findByRestaurantIdAndTableNumberAndDeletedIsTrue(Integer restaurantId, Integer tableNumber);

    Optional<AppTable> findByRestaurantIdAndTableNumberAndDeletedIsFalse(Integer restaurantId, Integer tableNumber);

    Optional<AppTable> findByQrId(Integer qrId);

    List<AppTable> findByRestaurantIdAndTableNumberIn(Integer restaurantId, List<Integer> tableNumbers);

    @Modifying
    @Query("UPDATE AppTable a SET a.deleted = true WHERE a.id = :appTableId")
    void softDeleteAppTable(Integer appTableId);

    @Modifying
    @Query("UPDATE AppTable a SET a.deleted = true WHERE a.restaurant.id = :restaurantId")
    void softDeleteAppTablesByRestaurantId(Integer restaurantId);
}

