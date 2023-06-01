package com.blankfactor.ra.repository;


import com.blankfactor.ra.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Table, Integer> {
    @Query("SELECT t FROM Table t WHERE t.name = :name")
    Table findTableByName(@Param("name") String name);
}

