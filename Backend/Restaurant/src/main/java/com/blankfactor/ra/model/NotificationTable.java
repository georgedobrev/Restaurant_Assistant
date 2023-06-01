package com.blankfactor.ra.model;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "notification_table", schema = "dbo", catalog = "restaurant_assistant")
public class NotificationTable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "table_id")
    private int tableId;

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "message")
    private String message;

    @Column(name = "approved")
    private Boolean approved;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User userId;
}