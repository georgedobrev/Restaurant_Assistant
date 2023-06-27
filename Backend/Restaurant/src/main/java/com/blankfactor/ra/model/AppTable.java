package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "AppTable")
@Table(name = "app_table", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"restaurant_id", "table_number"})
})
public class AppTable extends Audit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "table_number")
    private int tableNumber;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "qr_id")
    private QrCode qr;

    @Builder.Default
    @Column(name = "occupied")
    private boolean occupied = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "section_id")
    private Section section;

    @Column(name = "capacity")
    private int capacity;

    @Builder.Default
    @Column(name = "virtual_table")
    private boolean isVirtualTable = false;

    @Builder.Default
    @Column(name = "active")
    private boolean active = true;
}