package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QrCodeRepository extends JpaRepository<QrCode, Integer> {
    @Query("SELECT qr FROM QRCode qr WHERE qr.id IN :ids")
    List<QrCode> findAllByIds(@Param("ids") List<Integer> ids);

    @Query("SELECT t.tableId FROM Table t WHERE t.qrCode.id = :qrCodeId")
    Integer findTableIdByQrCodeId(@Param("qrCodeId") Integer qrCodeId);
}
