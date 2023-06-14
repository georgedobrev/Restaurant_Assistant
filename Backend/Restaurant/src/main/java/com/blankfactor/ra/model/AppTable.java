package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "AppTable")
@Table(
        name = "app_table",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"restaurant_id", "table_number"})
        })
public class AppTable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "table_number")
    private int tableNumber;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "qr_id")
    private QrCode qr;

    @Column(name = "occupied")
    private boolean occupied;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "section_id")
    private Section section;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "virtual_table")
    private boolean isVirtualTable;

    @Column(name = "active")
    private boolean active;
}