package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "reporting")
public class Reporting {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "table_id", referencedColumnName = "id", nullable = false)
    @Column(name = "table_id")
    private AppTable tableId;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    @Column(name = "restaurant_id")
    private Restaurant restaurantId;

    @ManyToOne
    @JoinColumn(name = "report_from", referencedColumnName = "id", nullable = false)
    @Column(name = "report_from")
    private AppUser reportFrom;

    @ManyToOne
    @JoinColumn(name = "report_to", referencedColumnName = "id", nullable = false)
    @Column(name = "report_to")
    private AppUser reportTo;
}
