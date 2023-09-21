package com.merrycorner.repository;

import com.merrycorner.entity.SpringSecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringSecurityUserRepository extends JpaRepository<SpringSecurityUser, Long> {
    Optional<SpringSecurityUser> findByUsername(String username);
    Optional<SpringSecurityUser> findByResetPasswordToken(String token);
}
