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
}