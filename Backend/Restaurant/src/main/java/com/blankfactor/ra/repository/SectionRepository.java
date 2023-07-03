package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Integer> {
    List<Section> findByRestaurant(Restaurant restaurant);
    // TODO: research
//    @Query(value = """
//            SELECT *
//            FROM [section] s\s
//            WHERE restaurant_id = :restaurantId
//            AND (table_numbers LIKE '%:tableNumber%');
//            """, nativeQuery = true)
//    List<Section> findByRestaurantIdAndTableNumberInSection(@Param("restaurantId") Integer restaurantId, @Param("tableNumber") Integer tableNumber);
}