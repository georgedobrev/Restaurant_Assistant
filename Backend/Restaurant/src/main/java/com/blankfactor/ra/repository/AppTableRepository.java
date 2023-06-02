package com.blankfactor.ra.repository;


import com.blankfactor.ra.model.AppTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppTableRepository extends JpaRepository<AppTable, Integer> {
    List<AppTable> findByRestaurantId(Integer restaurantId);

    AppTable findByRestaurantIdAndTableNumber(Integer restaurantId, Integer tableNumber);

    @Query("SELECT rt.qr.id FROM AppTable rt WHERE rt.restaurant.id = :restaurantId AND rt.tableNumber IN :tableNumbers")
    List<Integer> findQRIdByRestaurantIdAndTableNumbers(@Param("restaurantId") Integer restaurantId, @Param("tableNumbers") List<Integer> tableNumbers);

}

