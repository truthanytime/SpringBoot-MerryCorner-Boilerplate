package com.merrycorner.repository;

import com.merrycorner.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    // Add any custom queries if needed
}
