package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
public class Table {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "qr_id")
    private Integer qrId;

    @Basic
    @Column(name = "occupied")
    private Boolean occupied;

    @Basic
    @Column(name = "restaurant_id")
    private int restaurantId;

    @Basic
    @Column(name = "capacity")
    private Integer capacity;

    @Basic
    @Column(name = "virtual_table")
    private Boolean virtualTable;

    @Basic
    @Column(name = "active")
    private Boolean active;
}