package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

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

    @OneToMany(mappedBy = "userByUserId")
    private Collection<NotificationTable> notificationTablesById;

    @OneToMany(mappedBy = "userByReportFrom")
    private Collection<Reporting> reportsById;

    @OneToMany(mappedBy = "userByReportTo")
    private Collection<Reporting> reportsToId;

    @OneToMany(mappedBy = "userByUserId")
    private Collection<UserRole> userRolesById;

    @OneToMany(mappedBy = "userByUserId")
    private Collection<UserTable> userTablesByUserId;

    @OneToMany(mappedBy = "userByWaiterId")
    private Collection<UserTable> userTablesByWaiterId;
}