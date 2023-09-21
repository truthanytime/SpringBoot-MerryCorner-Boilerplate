package com.merrycorner.repository;

import com.merrycorner.entity.VendorCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorCategoryRepository extends JpaRepository<VendorCategory, Long> {
    // Add any custom queries if needed
}
