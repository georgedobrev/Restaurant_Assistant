package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.WaiterSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaiterSectionRepository extends JpaRepository<WaiterSection, Integer> {
    List<WaiterSection> findBySectionId(int sectionId);
}