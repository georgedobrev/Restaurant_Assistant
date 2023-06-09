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
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"table_numbers", "restaurant_id"}),
                @UniqueConstraint(columnNames = {"section_name", "restaurant_id"})
        }
)
public class Section extends Audit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "section_name", nullable = false)
    private String sectionName;

    @Column(name = "table_numbers")
    private String tableNumbers;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}