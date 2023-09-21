package com.merrycorner.repository;

import com.merrycorner.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    // Add any custom queries if needed
}
