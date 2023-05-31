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

    @Column(name = "table_id")
    private int tableId;

    @Column(name = "restaurant_id")
    private int restaurantId;

    @Column(name = "report_from")
    private int reportFrom;

    @Column(name = "report_to")
    private int reportTo;

    @ManyToOne
    @JoinColumn(name = "table_id", referencedColumnName = "id", nullable = false)
    private Table tableByTableId;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    private Restaurant restaurantByRestaurantId;

    @ManyToOne
    @JoinColumn(name = "report_from", referencedColumnName = "id", nullable = false)
    private User userByReportFrom;

    @ManyToOne
    @JoinColumn(name = "report_to", referencedColumnName = "id", nullable = false)
    private User userByReportTo;
}