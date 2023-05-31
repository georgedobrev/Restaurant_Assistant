package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
public class Reporting {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "table_id")
    private int tableId;
    @Basic
    @Column(name = "restaurant_id")
    private int restaurantId;
    @Basic
    @Column(name = "report_from")
    private int reportFrom;
    @Basic
    @Column(name = "report_to")
    private int reportTo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reporting reporting = (Reporting) o;
        return id == reporting.id && tableId == reporting.tableId && restaurantId == reporting.restaurantId && reportFrom == reporting.reportFrom && reportTo == reporting.reportTo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tableId, restaurantId, reportFrom, reportTo);
    }
}