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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QrCode qrCode = (QrCode) o;
        return id == qrCode.id && Arrays.equals(qrImg, qrCode.qrImg);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id);
        result = 31 * result + Arrays.hashCode(qrImg);
        return result;
    }
}