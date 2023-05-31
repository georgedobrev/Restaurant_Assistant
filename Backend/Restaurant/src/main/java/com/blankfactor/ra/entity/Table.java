package com.blankfactor.ra.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@jakarta.persistence.Table(name = "table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer tableId;

    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "qr_id")
    private QRCode qrCode;

    // @Column(name = "occupied")
    private boolean occupied;

    @Column(name = "capacity")
    private int capacity;


    @Column(name = "virtual_table")
    private boolean virtualTable;

    @Column(name = "active")
    private boolean active;
}

