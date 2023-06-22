package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.Sysadmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysadminRepository extends JpaRepository<Sysadmin, Integer> {
    //List<Sysadmin> findAllByRestaurantId(int restaurantId);
}
