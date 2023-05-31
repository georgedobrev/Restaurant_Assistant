package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
public class Restaurant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "tables_count")
    private Integer tablesCount;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "phone_number1")
    private String phoneNumber1;
    @Basic
    @Column(name = "phone_number2")
    private String phoneNumber2;
    @Basic
    @Column(name = "phone_number3")
    private String phoneNumber3;
    @Basic
    @Column(name = "active")
    private Boolean active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(tablesCount, that.tablesCount) && Objects.equals(address, that.address) && Objects.equals(phoneNumber1, that.phoneNumber1) && Objects.equals(phoneNumber2, that.phoneNumber2) && Objects.equals(phoneNumber3, that.phoneNumber3) && Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tablesCount, address, phoneNumber1, phoneNumber2, phoneNumber3, active);
    }
}