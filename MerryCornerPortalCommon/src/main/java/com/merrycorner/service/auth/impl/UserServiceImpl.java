package com.merrycorner.service.auth.impl;

import com.merrycorner.constant.Constant;
import com.merrycorner.entity.SpringSecurityAuthority;
import com.merrycorner.entity.SpringSecuritySocialUser;
import com.merrycorner.entity.SpringSecurityUser;
import com.merrycorner.entity.UserProfile;
import com.merrycorner.repository.SpringSecurityAuthorityRepository;
import com.merrycorner.repository.SpringSecuritySocialUserRepository;
import com.merrycorner.repository.SpringSecurityUserRepository;
import com.merrycorner.repository.UserProfileRepository;
import com.merrycorner.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SpringSecurityUserRepository userRepository;

    @Autowired
    private SpringSecuritySocialUserRepository socialUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SpringSecurityAuthorityRepository authorityRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public SpringSecurityUser registerUser(SpringSecurityUser user, UserProfile userProfile) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user = userRepository.save(user);

        // Assign the desired role to the user
        SpringSecurityAuthority authority = new SpringSecurityAuthority();
        authority.setAuthority(Constant.ROLE_HOST);
        authority.setUser(user);

        // Save the authority to associate it with the user
        authorityRepository.save(authority);

        // Save profile
        userProfile.setSpringSecurityUser( user );
        userProfile.setEmailAddress(user.getUsername());
        userProfileRepository.save(userProfile);

        return user;
    }
    @Override
    public SpringSecurityUser updatePassword(SpringSecurityUser user, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        user.setResetPasswordToken(null);
        userRepository.save(user);
        return user;
    }

    @Override
    public SpringSecurityUser updateResetPasswordToken(String token, String username) throws UsernameNotFoundException {
        SpringSecurityUser user = getUserByUsername(username);
        if (!existsByUsername(username)) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        else {
            user.setResetPasswordToken(token);
            userRepository.save(user);
            return user;
        }
    }

    @Override
    public SpringSecurityUser getByResetPasswordToken(String token) {
        Optional<SpringSecurityUser> candidate = userRepository.findByResetPasswordToken(token);
        return candidate.orElse(null);
    }
    @Override
    public SpringSecuritySocialUser registerSocialUser(
            String provider,
            String providerUserId,
            String email,
            SpringSecurityUser user) {

        SpringSecuritySocialUser socialUser = new SpringSecuritySocialUser();
        socialUser.setProvider(provider);
        socialUser.setProviderUserId(providerUserId);
        socialUser.setEmail(email);
        socialUser.setEnabled(true);
        socialUser.setSpringSecurityUser(user);

        return socialUserRepository.save(socialUser);
    }
    @Override
    public SpringSecurityUser getUserByUsername(String username) {
        Optional<SpringSecurityUser> candidate = userRepository.findByUsername(username);
        return candidate.orElse(null);
    }

    @Override
    public boolean existsByUsername(String username) {
        Optional<SpringSecurityUser> candidate = userRepository.findByUsername(username);
        return candidate.isPresent();
    }

    @Override
    public UserProfile createUserProfileTemplate(String fullName) {

        String[] nameParts = fullName.trim().split("\\s+");
        String firstName = "";
        StringBuilder middleName = new StringBuilder();
        String lastName = "";

        if (nameParts.length >= 1) {
            firstName = nameParts[0];
        }
        if (nameParts.length >= 2) {
            lastName = nameParts[nameParts.length - 1];
        }
        if (nameParts.length >= 3) {
            for (int i = 1; i < nameParts.length - 1; i++) {
                middleName.append(nameParts[i]).append(" ");
            }
            middleName = new StringBuilder(middleName.toString().trim()); // Remove trailing whitespace
        }

        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName(firstName);
        userProfile.setMiddleName(middleName.toString());
        userProfile.setLastName(lastName);
        return userProfile;
    }
}
