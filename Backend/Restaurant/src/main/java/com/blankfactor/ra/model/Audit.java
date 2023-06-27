package com.blankfactor.ra.model;

import com.blankfactor.ra.config.InstantSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Audit {
    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    @CreatedDate
    @JsonSerialize(using = InstantSerializer.class)
    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @LastModifiedDate
    @JsonSerialize(using = InstantSerializer.class)
    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();
}