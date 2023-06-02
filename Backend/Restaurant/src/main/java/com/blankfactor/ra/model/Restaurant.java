package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
public class Restaurant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "tables_count")
    private Integer tablesCount;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number1")
    private String phoneNumber1;

    @Column(name = "phone_number2")
    private String phoneNumber2;

    @Column(name = "phone_number3")
    private String phoneNumber3;

    @Column(name = "active")
    private Boolean active;
}