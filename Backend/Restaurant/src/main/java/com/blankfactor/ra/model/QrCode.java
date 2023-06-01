package com.blankfactor.ra.model;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
@Table(name = "qr_code", schema = "dbo", catalog = "restaurant_assistant")
public class QrCode {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "qr_img")
    private byte[] qrImg;

    @OneToMany(mappedBy = "qrCodeByQrId")
    private Collection<com.blankfactor.ra.model.Table> tablesById;
}