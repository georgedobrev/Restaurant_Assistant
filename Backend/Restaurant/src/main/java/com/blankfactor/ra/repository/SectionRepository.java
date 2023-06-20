package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Integer> {
    List<Section> findByRestaurant(Restaurant restaurant);
}
