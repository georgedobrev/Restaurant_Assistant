package com.blankfactor.ra.model;

import com.blankfactor.ra.config.InstantSerializer;
import com.blankfactor.ra.enums.RequestType;
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
@Table(name = "notification")
public class Notification {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by", nullable = false)
    private AppUser createdBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "updated_by")
    private AppUser updatedBy;

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

    @Builder.Default
    @Column(name = "created_at")
    @JsonSerialize(using = InstantSerializer.class)
    private Instant createdAt = Instant.now();

    @Builder.Default
    @Column(name = "updated_at")
    @JsonSerialize(using = InstantSerializer.class)
    private Instant updatedAt = Instant.now();
}