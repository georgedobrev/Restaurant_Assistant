package com.blankfactor.ra.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "user_table", schema = "dbo", catalog = "restaurant_assistant")
public class UserTable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "user_id")
    private int userId;

    @Basic
    @Column(name = "waiter_id")
    private int waiterId;

    @Basic
    @Column(name = "table_id")
    private int tableId;

    @Basic
    @Column(name = "start_time")
    private Date startTime;

    @Basic
    @Column(name = "end_time")
    private Date endTime;
}