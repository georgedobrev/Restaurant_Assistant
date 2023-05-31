package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Objects;

@Data
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "surname")
    private String surname;

    @Basic
    @Column(name = "created_at")
    private Date createdAt;

    @Basic
    @Column(name = "blacklisted")
    private Boolean blacklisted;

    @Basic
    @Column(name = "active")
    private Boolean active;
}