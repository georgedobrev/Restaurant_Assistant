package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.Reporting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportingRepository extends JpaRepository<Reporting, Integer> {
}
