package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
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
