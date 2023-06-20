package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;

import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sysadmin {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "restaurant_id")
    private Integer restaurantId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Builder.Default
    @Column(name = "blacklisted")
    private Boolean blacklisted = false;

    @Builder.Default
    @Column(name = "active")
    private Boolean active = true;

    @Builder.Default
    @Column(name = "created_at")
    private Instant createdAt = Instant.now();
}