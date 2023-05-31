package com.blankfactor.ra.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Arrays;
import java.util.Objects;

@Data
@Entity
@Table(name = "qr_code", schema = "dbo", catalog = "restaurant_assistant")
public class QrCode {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "qr_img")
    private byte[] qrImg;
}