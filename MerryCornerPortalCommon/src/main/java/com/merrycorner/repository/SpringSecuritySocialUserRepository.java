package com.merrycorner.repository;

import com.merrycorner.entity.SpringSecuritySocialUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringSecuritySocialUserRepository extends JpaRepository<SpringSecuritySocialUser, Long> {
    // Add any custom queries if needed
}
