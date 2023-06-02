package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;

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
    @JoinColumn(name = "table_id")
    private Table table;

    @Column(name = "people_count")
    private Integer peopleCount;

    @Column(name = "reservation_time")
    private Instant reservationTime;

    @Column(name = "client_phone_number")
    private String clientPhoneNumber;
}