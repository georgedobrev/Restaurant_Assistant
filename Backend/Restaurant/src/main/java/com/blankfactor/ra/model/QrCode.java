package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;

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

    @OneToOne(mappedBy = "qr", fetch = FetchType.LAZY)
    private AppTable appTable;
}