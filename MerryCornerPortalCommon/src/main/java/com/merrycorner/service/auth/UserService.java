package com.merrycorner.service.auth;

import com.merrycorner.entity.SpringSecuritySocialUser;
import com.merrycorner.entity.SpringSecurityUser;
import com.merrycorner.entity.UserProfile;

public interface UserService {
    SpringSecurityUser registerUser(SpringSecurityUser user, UserProfile userProfile);
    SpringSecuritySocialUser registerSocialUser(String provider,
                                                String providerUserId,
                                                String email,
                                                SpringSecurityUser user);
    SpringSecurityUser getUserByUsername(String username);
    SpringSecurityUser updatePassword(SpringSecurityUser user, String newPassword);
    SpringSecurityUser updateResetPasswordToken(String token, String username);
    SpringSecurityUser getByResetPasswordToken(String token);
    boolean existsByUsername(String username);
    UserProfile createUserProfileTemplate(String fullName);
}
