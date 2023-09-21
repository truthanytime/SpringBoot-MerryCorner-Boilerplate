package com.merrycorner.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "spring_security_authorities")
public class SpringSecurityAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Define roles without "ROLE_" prefix
    private String authority;

    @ManyToOne
    @JoinColumn(name = "spring_security_user_id")
    private SpringSecurityUser user;

    // Getters and setters
}
