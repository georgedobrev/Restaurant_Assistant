package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Reporting {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "table_id", referencedColumnName = "id", nullable = false)
    private Table tableId;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    private Restaurant restaurantId;

    @ManyToOne
    @JoinColumn(name = "report_from", referencedColumnName = "id", nullable = false)
    private User reportFrom;

    @ManyToOne
    @JoinColumn(name = "report_to", referencedColumnName = "id", nullable = false)
    private User reportTo;
}