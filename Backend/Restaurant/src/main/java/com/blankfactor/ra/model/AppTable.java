package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "AppTable")
@Table(name = "app_table", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"restaurant_id", "table_number"})
})
public class AppTable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "table_number")
    private int tableNumber;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "qr_id")
    private QrCode qr;

    @Builder.Default
    @Column(name = "occupied")
    private boolean occupied = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "capacity")
    private int capacity;

    @Builder.Default
    @Column(name = "virtual_table")
    private boolean isVirtualTable = false;

    @Builder.Default
    @Column(name = "active")
    private boolean active = true;
}