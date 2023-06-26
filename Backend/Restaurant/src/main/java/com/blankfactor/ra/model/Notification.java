package com.blankfactor.ra.model;

import com.blankfactor.ra.config.InstantSerializer;
import com.blankfactor.ra.enums.RequestType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@SuperBuilder()
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "notification")
public class Notification extends Audit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_table_id", nullable = false)
    private AppTable appTable;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_type", nullable = false)
    private RequestType requestType;

    @Column(name = "message")
    private String message;

    @Builder.Default
    @Column(name = "approved")
    private Boolean approved = false;
}