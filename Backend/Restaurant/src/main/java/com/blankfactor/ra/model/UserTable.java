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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTable userTable = (UserTable) o;
        return id == userTable.id && userId == userTable.userId && waiterId == userTable.waiterId && tableId == userTable.tableId && Objects.equals(startTime, userTable.startTime) && Objects.equals(endTime, userTable.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, waiterId, tableId, startTime, endTime);
    }
}