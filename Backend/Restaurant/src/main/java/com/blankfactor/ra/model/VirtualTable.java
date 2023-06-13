package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "virtual_table", schema = "dbo", catalog = "restaurant_assistant")
public class VirtualTable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "tables_ids")
    private String tablesIds;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}