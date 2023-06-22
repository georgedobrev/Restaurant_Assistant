package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QrCodeRepository extends JpaRepository<QrCode, Integer> {
    Optional<QrCode> findByHashedUrl(String hashedURL);
}
