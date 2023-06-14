package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Section")
@Table(
        name = "section",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"table_numbers", "restaurant_id"})
)
public class Section {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "section_number", unique = true)
    private int sectionNumber;
    @Column(name = "table_numbers", unique = true)
    private String tableNumbers;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @OneToOne
    @JoinColumn(name = "waiter_id", unique = true)
    private AppUser waiter;
}