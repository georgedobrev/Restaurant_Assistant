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
    @Column(name = "section_name", nullable = false)
    private String sectionName;
    @Column(name = "table_numbers")
    private String tableNumbers;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}
