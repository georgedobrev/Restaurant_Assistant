package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@MappedSuperclass
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Audit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private AppUser createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    private AppUser updatedBy;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}