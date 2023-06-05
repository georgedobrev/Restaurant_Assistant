package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Entity
@Getter
@Setter
@Table(name = "notification_table", schema = "dbo", catalog = "restaurant_assistant")
public class NotificationTable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id", nullable = false)
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_table_id", nullable = false)
    private AppTable appTable;

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "time")
    private LocalDate time;

    @Column(name = "message")
    private String message;

    @Column(name = "approved")
    private Boolean approved;


    public void setTime(LocalDate time) {
        time = LocalDate.now();
        this.time = time;
    }
}