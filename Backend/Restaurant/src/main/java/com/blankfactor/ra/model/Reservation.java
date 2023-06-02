package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
public class Reservation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_table_id")
    private AppTable appTable;

    @Column(name = "people_count")
    private Integer peopleCount;

    @Column(name = "reservation_time")
    private Instant reservationTime;

    @Column(name = "client_phone_number")
    private String clientPhoneNumber;
}