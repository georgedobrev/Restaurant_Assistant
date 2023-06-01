package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Table {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "qr_id")
    private Integer qrId;

    @Column(name = "occupied")
    private Boolean occupied;

    @Column(name = "restaurant_id")
    private int restaurantId;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "virtual_table")
    private Boolean virtualTable;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "tableByTableId")
    private Collection<Reporting> reportsById;

    @OneToMany(mappedBy = "tableByTableId")
    private Collection<Reservation> reservationsById;

    @OneToOne
    @JoinColumn(name = "qr_id", referencedColumnName = "id")
    private QrCode qrCodeByQrId;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    private Restaurant restaurantByRestaurantId;

    @OneToMany(mappedBy = "tableByTableId")
    private Collection<UserTable> userTablesById;
}

