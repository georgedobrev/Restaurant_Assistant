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
}