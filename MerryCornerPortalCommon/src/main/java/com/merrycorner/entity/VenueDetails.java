package com.merrycorner.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class VenueDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String venueName;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    // Getters and setters
}