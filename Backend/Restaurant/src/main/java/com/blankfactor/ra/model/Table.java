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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return id == table.id && restaurantId == table.restaurantId && Objects.equals(qrId, table.qrId) && Objects.equals(occupied, table.occupied) && Objects.equals(capacity, table.capacity) && Objects.equals(virtualTable, table.virtualTable) && Objects.equals(active, table.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qrId, occupied, restaurantId, capacity, virtualTable, active);
    }
}