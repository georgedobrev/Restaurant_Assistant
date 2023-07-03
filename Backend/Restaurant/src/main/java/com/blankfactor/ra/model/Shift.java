package com.blankfactor.ra.model;

import com.blankfactor.ra.enums.DayType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
        name = "shift",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"restaurant_id", "start_time", "end_time", "day_from", "day_to"})
)
public class Shift extends Audit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    // TODO format correctly
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_from", nullable = false)
    private DayType dayFrom;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_to", nullable = false)
    private DayType dayTo;
}