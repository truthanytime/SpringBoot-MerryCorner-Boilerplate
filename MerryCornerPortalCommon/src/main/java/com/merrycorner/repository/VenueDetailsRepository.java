package com.merrycorner.repository;

import com.merrycorner.entity.VenueDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueDetailsRepository extends JpaRepository<VenueDetails, Long> {
    // Add any custom queries if needed
}
