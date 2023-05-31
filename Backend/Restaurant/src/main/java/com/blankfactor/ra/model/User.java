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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(email, user.email) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(createdAt, user.createdAt) && Objects.equals(blacklisted, user.blacklisted) && Objects.equals(active, user.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, surname, createdAt, blacklisted, active);
    }
}