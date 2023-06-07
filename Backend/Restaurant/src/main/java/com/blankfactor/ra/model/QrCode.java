package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "qr_code", schema = "dbo", catalog = "restaurant_assistant")
@NoArgsConstructor
public class QrCode {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "hashed_url")
    private String hashedUrl;

    @Column(name = "qr_img")
    private byte[] qrImg;

    public QrCode(byte[] qrText, String hashedUrl) {
        this.qrImg = qrText;
        this.hashedUrl = hashedUrl;
    }
}