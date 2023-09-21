package com.merrycorner.repository;

import com.merrycorner.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    // Add any custom queries if needed
}
