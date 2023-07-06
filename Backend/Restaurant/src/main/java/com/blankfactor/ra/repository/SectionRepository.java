package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.Section;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Transactional
@Repository
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
    List<Section> findAllByTableNumbersContains(String tableId);

    Optional<Section> findByRestaurantIdAndTableNumbersAndDeletedIsTrue(Integer restaurantId, String tableNumbers);

    Optional<Section> findByRestaurantIdAndSectionNameAndDeletedIsTrue(Integer restaurantId, String sectionName);

    @Modifying
    @Query("UPDATE Section s SET s.deleted = true WHERE s.id = :sectionId")
    void softDeleteSection(Integer sectionId);
}
