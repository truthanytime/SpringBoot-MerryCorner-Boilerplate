package com.merrycorner.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zipCode;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<UserProfile> userProfiles = new ArrayList<>();

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<Vendor> vendors = new ArrayList<>();

    // Other fields, getters and setters
}
