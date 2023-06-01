package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
public class Reservation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "people_count")
    private Integer peopleCount;

    @Column(name = "reservation_time")
    private Date reservationTime;

    @Column(name = "client_phone_number")
    private String clientPhoneNumber;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    private Restaurant restaurantId;

    @ManyToOne
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    private Table tableId;
}