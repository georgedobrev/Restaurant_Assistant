package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "notification_table")
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notification_id")
    private int notification_id;
    @Column(name = "app_user_id")
    private int app_user_id;
    @Column(name = "app_table_id")
    private int app_table_id;
    @Column(name = "request_type")
    private String request_type;
    @Column(name = "time")
    private LocalDate time;
    @Column(name = "message")
    private String message;
    @Column(name = "approved")
    private boolean approved;


    public void setNotification_id(int id) {
        this.notification_id = id;
    }

    public int getNotification_id() {
        return notification_id;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        time = LocalDate.now();
        this.time = time;
    }
}
