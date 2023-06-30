package com.blankfactor.ra.model;

import com.blankfactor.ra.config.InstantSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reservation extends Audit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_table_id")
    private AppTable appTable;

    @Column(name = "people_count")
    private Integer peopleCount;

    @Column(name = "reservation_time")
    @JsonSerialize(using = InstantSerializer.class)
    private Instant reservationTime;

    @Column(name = "client_phone_number")
    private String clientPhoneNumber;
}