package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue
    public Integer id;
    @Column(unique = true)
    public String token;
    public boolean revoked;
    public boolean expired;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    public AppUser appUser;
}
