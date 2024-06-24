package com.project.readers.readers_community.controllers;

import com.project.readers.readers_community.DTOs.AuthDTO;
import com.project.readers.readers_community.annotations.CurrentUser;
import com.project.readers.readers_community.entities.MemberApprovalRequest;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.services.AuthService;
import com.project.readers.readers_community.utilities.LoginRequest;
import org.springframework.web.bind.annotation.*;

// for authentication purposes
@RestController
@RequestMapping("/auth")
public class AuthController
{

    // auth service
    private final AuthService authService;

    // DI
    public AuthController(AuthService authService)
    {
        this.authService = authService;
    }

    //endpoint to verify JWT and fetch basic user identification details
    @GetMapping
    public AuthDTO getAuthDetails(@CurrentUser User currentUser)
    {
        return authService.getAuthDetails(currentUser);
    }

    // method to sign up
    @PostMapping("/signup")
    public String signup(@RequestBody User user)
    {
        return authService.signup(user);
    }

    // method to verify otp
    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam("email") String email, @RequestParam("otp") String otp)
    {
        return authService.verifyOtp(email, otp);
    }

    // method to send otp again for signup
    @PostMapping("/sendOtp")
    public String sendOtp(@RequestParam("email") String email)
    {
        return authService.sendOtp(email);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest)
    {
        return authService.login(loginRequest);
    }
}


