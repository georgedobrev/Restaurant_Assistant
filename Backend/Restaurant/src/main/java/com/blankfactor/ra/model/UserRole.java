package com.blankfactor.ra.model;

import com.blankfactor.ra.enums.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_role")
@IdClass(UserRolePK.class)
public class UserRole extends Audit {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id", nullable = false)
    @NotNull
    private AppUser appUser;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    @NotNull
    private Restaurant restaurant;

    @Enumerated(EnumType.STRING)
    @Id
    @Column(name = "role_type", nullable = false)
    @NotNull
    private RoleType roleType;

    @Builder.Default
    @Column(name = "deleted")
    private Boolean deleted = false;
}