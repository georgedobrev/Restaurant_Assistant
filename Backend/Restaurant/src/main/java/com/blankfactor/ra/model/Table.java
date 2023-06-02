package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
public class Table {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "table_number")
    private int tableNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qr_id")
    private QrCode qr;

    @Column(name = "occupied")
    private boolean occupied;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "virtual_table")
    private boolean isVirtualTable;

    @Column(name = "active")
    private boolean active;
}