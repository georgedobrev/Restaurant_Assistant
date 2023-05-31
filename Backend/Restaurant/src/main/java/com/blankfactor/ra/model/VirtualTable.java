package com.blankfactor.ra.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "virtual_table", schema = "dbo", catalog = "restaurant_assistant")
public class VirtualTable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "tables_ids")
    private String tablesIds;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VirtualTable that = (VirtualTable) o;
        return id == that.id && Objects.equals(tablesIds, that.tablesIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tablesIds);
    }
}