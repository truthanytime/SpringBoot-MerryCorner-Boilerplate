package com.merrycorner.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;
    private String emailAddress;

    @OneToOne
    @JoinColumn(name = "spring_security_user_id")
    private SpringSecurityUser springSecurityUser;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    // Getters and setters
}