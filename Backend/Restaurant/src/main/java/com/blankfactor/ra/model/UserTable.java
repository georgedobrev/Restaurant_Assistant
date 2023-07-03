package com.blankfactor.ra.model;

import com.blankfactor.ra.config.InstantSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Data
@Entity
@Table(name = "user_table")
@NoArgsConstructor
@AllArgsConstructor
public class UserTable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id", nullable = false)
    private AppUser appUser;

    @Column(name = "waiter_ids")
    private String waiterIds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_table_id")
    private AppTable appTable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merged_table_id")
    private MergedTable mergedTable;

    @Column(name = "start_time")
    @JsonSerialize(using = InstantSerializer.class)
    private Instant startTime;

    @Column(name = "end_time")
    @JsonSerialize(using = InstantSerializer.class)
    private Instant endTime;
}