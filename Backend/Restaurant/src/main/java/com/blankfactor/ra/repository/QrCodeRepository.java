package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QrCodeRepository extends JpaRepository<QrCode, Integer> {
    List<QrCode> findQRCodesByIdIn(List<Integer> qrIds);

    @Query("SELECT rt.tableNumber FROM AppTable rt WHERE rt.qr.id = :qrCodeId")
    Integer findTableNumberByQrCodeId(@Param("qrCodeId") Integer qrCodeId);
}
