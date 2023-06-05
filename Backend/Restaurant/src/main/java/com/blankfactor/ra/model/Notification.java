package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "notification", schema = "dbo", catalog = "restaurant_assistant")
public class Notification {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id", nullable = false)
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_table_id", nullable = false)
    private AppTable appTable;

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "message")
    private String message;

    @Column(name = "time")
    private Instant time;

    @Column(name = "approved")
    private Boolean approved;
}