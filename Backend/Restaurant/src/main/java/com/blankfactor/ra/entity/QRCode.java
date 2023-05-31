package com.blankfactor.ra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@jakarta.persistence.Table(name = "qr_code")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QRCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qr_id")
    private Integer id;

    @Column(name = "qr_img", columnDefinition = "BLOB")
    private byte[] qrImg;

    public QRCode(byte[] qrImg) {
        this.qrImg = qrImg;
    }
}

