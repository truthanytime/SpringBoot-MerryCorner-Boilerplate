package com.merrycorner.service.auth.impl;

import com.merrycorner.entity.SpringSecurityAuthority;
import com.merrycorner.entity.SpringSecurityUser;
import com.merrycorner.repository.SpringSecurityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SpringSecurityUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SpringSecurityUser> candidate = userRepository.findByUsername(username);
        if (candidate.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        SpringSecurityUser user = candidate.get();
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getAuthorities().stream()
                        .map(SpringSecurityAuthority::getAuthority)
                        .toArray(String[]::new))
                .build();
    }
}
