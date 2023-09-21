package com.merrycorner.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "spring_security_user")
public class SpringSecurityUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    @Column(name = "reset_password_token")
    private String resetPasswordToken;
    private boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SpringSecurityAuthority> authorities = new ArrayList<>();

    @OneToOne(mappedBy = "springSecurityUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserProfile userProfile;

    // Getters and setters
}

