package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QrCodeRepository extends JpaRepository<QrCode, Integer> {
    List<QrCode> findByAppTableIn(List<AppTable> appTables);
}
