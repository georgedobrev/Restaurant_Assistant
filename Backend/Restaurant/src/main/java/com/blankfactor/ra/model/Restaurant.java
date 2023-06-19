package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    //@Formula(value = "(SELECT COUNT(*) FROM app_table WHERE restaurant_id = id)")    @Column(name = "tables_count")
    private Integer tablesCount; //= setTablesCount(id);

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number1")
    private String phoneNumber1;

    @Column(name = "phone_number2")
    private String phoneNumber2;

    @Column(name = "phone_number3")
    private String phoneNumber3;

    @Builder.Default
    @Column(name = "active")
    private Boolean active = true;

}