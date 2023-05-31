package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Objects;

@Data
@Entity
public class Reservation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "restaurant_id")
    private int restaurantId;
    @Basic
    @Column(name = "table_id")
    private Integer tableId;
    @Basic
    @Column(name = "people_count")
    private Integer peopleCount;
    @Basic
    @Column(name = "reservation_time")
    private Date reservationTime;
    @Basic
    @Column(name = "client_phone_number")
    private String clientPhoneNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id == that.id && restaurantId == that.restaurantId && Objects.equals(tableId, that.tableId) && Objects.equals(peopleCount, that.peopleCount) && Objects.equals(reservationTime, that.reservationTime) && Objects.equals(clientPhoneNumber, that.clientPhoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, restaurantId, tableId, peopleCount, reservationTime, clientPhoneNumber);
    }
}