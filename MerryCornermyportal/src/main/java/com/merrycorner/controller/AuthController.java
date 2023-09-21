package com.merrycorner.controller;

import com.merrycorner.constant.Constant;
import com.merrycorner.entity.SpringSecurityUser;
import com.merrycorner.entity.UserProfile;
import com.merrycorner.service.auth.impl.UserDetailsServiceImpl;
import com.merrycorner.service.auth.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;


@Controller
public class AuthController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new SpringSecurityUser());
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String registerUser(
            @ModelAttribute("user") SpringSecurityUser user,
            Model model,
            @RequestParam("fullname") String fullname,
            @RequestParam(value = "terms", required = false) String terms,
            @RequestParam("confirm_password") String confirm_password) {

        // validation
        String errorMessage = null;
        if (terms == null) {
            errorMessage = "Please agree to the terms and conditions.";
        } else if (!Objects.equals(user.getPassword(), confirm_password)) {
            errorMessage = "Passwords do not match.";
        } else if (userService.existsByUsername(user.getUsername())) {
            errorMessage = "Username is already taken. Please choose a different one.";
        }
        if( errorMessage != null ){
            model.addAttribute("errorMessage", errorMessage);
            return "auth/signup";
        }

        // Create user profile
        UserProfile userProfile = userService.createUserProfileTemplate(fullname);

        // Registration new user
        userService.registerUser(user, userProfile);

        // login
        //attemptLogin(user.getUsername());

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginUser(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            return "redirect:/";
        }

        return "auth/login";
    }

    @GetMapping("/login/oauth2/code/google/")
    public String oauth2LoginCallback(@AuthenticationPrincipal OAuth2User oauth2User) {

        // Handle user login/signup based on OAuth2User data
        String email = oauth2User.getAttribute("email"); // Get user's email from attributes

        // Check if the user's email already exists in your application's database
        boolean userExists = userService.existsByUsername(email);

        // User doesn't exist, create a new account and log them in
        if (!userExists) {

            // Create new user
            SpringSecurityUser newUser = new SpringSecurityUser();
            newUser.setUsername(email);
            newUser.setEnabled(true);

            // Create profile
            UserProfile tempProfile = new UserProfile();
            String fullName = oauth2User.getAttribute("name");
            if( fullName != null )
                tempProfile = userService.createUserProfileTemplate( fullName );

            // Save the new user
            newUser = userService.registerUser(newUser, tempProfile);

            // Create a corresponding SpringSecuritySocialUser and link it
            userService.registerSocialUser(
                    Constant.SOCIAL_USER_PROVIDER_GOOGLE,
                    oauth2User.getAttribute("sub"),
                    email,
                    newUser);
        }

        // login
        attemptLogin(email);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    private void attemptLogin( String email ){
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
