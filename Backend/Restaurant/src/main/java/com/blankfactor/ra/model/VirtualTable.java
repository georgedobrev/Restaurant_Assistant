package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "virtual_table")
public class VirtualTable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "table_numbers")
    private String tableNumbers;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}