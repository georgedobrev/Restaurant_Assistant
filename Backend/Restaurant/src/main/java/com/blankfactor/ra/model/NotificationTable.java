package com.blankfactor.ra.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "notification_table", schema = "dbo", catalog = "restaurant_assistant")
public class NotificationTable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "table_id")
    private int tableId;
    @Basic
    @Column(name = "request_type")
    private String requestType;
    @Basic
    @Column(name = "message")
    private String message;
    @Basic
    @Column(name = "approved")
    private Boolean approved;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTable that = (NotificationTable) o;
        return id == that.id && userId == that.userId && tableId == that.tableId && Objects.equals(requestType, that.requestType) && Objects.equals(message, that.message) && Objects.equals(approved, that.approved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, tableId, requestType, message, approved);
    }
}