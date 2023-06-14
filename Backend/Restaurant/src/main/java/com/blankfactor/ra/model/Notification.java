package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "notification")
public class Notification {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_user_id", nullable = false)
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_table_id", nullable = false)
    private AppTable appTable;

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "message")
    private String message;

    @Column(name = "approved")
    private Boolean approved;

    @Column(name = "created_at")
    private Instant createdAt;

}