package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "refresh_token")
public class RefreshToken extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @OneToOne
    @JoinColumn(name = "app_user_id", unique = true)
    public AppUser appUser;

    @Column(name = "token", unique = true)
    public String token;
}