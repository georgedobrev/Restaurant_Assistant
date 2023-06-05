package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QrCodeRepository extends JpaRepository<QrCode, Integer> {
    List<QrCode> findQRCodesByIdIn(Optional<List<Integer>> qrIds);
}
