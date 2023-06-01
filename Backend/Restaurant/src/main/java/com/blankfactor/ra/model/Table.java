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

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "tableId")
    private Collection<Reporting> reportsById;

    @OneToMany(mappedBy = "tableId")
    private Collection<Reservation> reservationsById;

    @ManyToOne
    @JoinColumn(name = "qr_id", referencedColumnName = "id")
    private QrCode qrId;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    private Restaurant restaurantId;

    @OneToMany(mappedBy = "tableId")
    private Collection<UserTable> tablesById;
}