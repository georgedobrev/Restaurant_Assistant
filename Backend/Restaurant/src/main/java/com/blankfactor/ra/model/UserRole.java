package com.blankfactor.ra.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "user_role", schema = "dbo", catalog = "restaurant_assistant")
@IdClass(UserRolePK.class)
public class UserRole {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "restaurant_id")
    private int restaurantId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id")
    private int roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return userId == userRole.userId && restaurantId == userRole.restaurantId && roleId == userRole.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, restaurantId, roleId);
    }
}