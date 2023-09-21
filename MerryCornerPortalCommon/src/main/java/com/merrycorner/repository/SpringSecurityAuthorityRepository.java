package com.merrycorner.repository;

import com.merrycorner.entity.SpringSecurityAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringSecurityAuthorityRepository extends JpaRepository<SpringSecurityAuthority, Long> {
    // Add any custom queries if needed
}
