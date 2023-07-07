package com.blankfactor.ra.repository;

import com.blankfactor.ra.enums.RoleType;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.UserRole;
import com.blankfactor.ra.model.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePK> {
    List<UserRole> findByAppUser_Id(int id);

    Optional<UserRole> findByAppUserAndRestaurantAndRoleType(AppUser appUser, Restaurant restaurant, RoleType roleType);

    List<UserRole> findByAppUserIdAndRoleType(int adminId, RoleType roleType);

    List<UserRole> findAllByRestaurantIdAndRoleType(int restaurantId, RoleType roleType);

    Optional<UserRole> findByAppUserAndRestaurant(AppUser appUser, Restaurant restaurant);
    UserRole findUserRoleByAppUser(AppUser appUser);
}