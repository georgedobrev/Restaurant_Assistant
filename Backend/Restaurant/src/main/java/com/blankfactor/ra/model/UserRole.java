package com.blankfactor.ra.model;

import com.blankfactor.ra.enums.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_role", schema = "dbo", catalog = "restaurant_assistant")
@IdClass(UserRolePK.class)
public class UserRole {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id", nullable = false)
    private AppUser appUser;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Enumerated(EnumType.STRING)
    @Id
    @Column(name = "role_type", nullable = false)
    private RoleType roleType;

    @ManyToOne
    private Tenant tenant;
}