package com.blankfactor.ra.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "notification", schema = "dbo", catalog = "restaurant_assistant")
public class Notification {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_user_id", nullable = false)
    private AppUser appUser;

    @JsonIgnore
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