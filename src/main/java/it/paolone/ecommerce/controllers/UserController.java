package it.paolone.ecommerce.controllers;

import org.springframework.web.bind.annotation.RestController;

import it.paolone.ecommerce.services.JwtService;
import it.paolone.ecommerce.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import it.paolone.ecommerce.configuration.filter.AuthRequest;
import it.paolone.ecommerce.entities.User;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/add_new_user")
    public String addNewUser(@RequestBody User User) {
        return this.userService.addUser(User);
    }

    @GetMapping("/user/user_profile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/admin_profile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generate_token")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getEmail());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

}
