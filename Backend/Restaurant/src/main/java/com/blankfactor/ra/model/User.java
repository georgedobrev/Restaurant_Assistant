package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Collection;

@Data
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "blacklisted")
    private Boolean blacklisted;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "userId")
    private Collection<NotificationTable> notificationTablesById;

    @OneToMany(mappedBy = "reportFrom")
    private Collection<Reporting> reportsById;

    @OneToMany(mappedBy = "reportTo")
    private Collection<Reporting> reportsToId;

    @OneToMany(mappedBy = "userId")
    private Collection<UserRole> userRolesById;

    @OneToMany(mappedBy = "userId")
    private Collection<UserTable> userTablesByUserId;

    @OneToMany(mappedBy = "waiterId")
    private Collection<UserTable> userTablesByWaiterId;
}