package com.merrycorner.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "spring_security_social_user")
public class SpringSecuritySocialUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provider;
    private String providerUserId;
    private String email;
    private boolean enabled;

    @OneToOne
    @JoinColumn(name = "spring_security_user_id")
    private SpringSecurityUser springSecurityUser;

    // Getters and setters
}
