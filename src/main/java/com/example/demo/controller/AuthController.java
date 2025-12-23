package com.example.demo.controller;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;

import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    // ---------------- REGISTER ----------------
    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest request) {

        User user = authService.register(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet())
        );
    }

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        User user = authService.authenticate(
                request.getEmail(),
                request.getPassword()
        );

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getId(),
                user.getRoles().stream()
                        .map(Role::getName)
                        .map(r -> "ROLE_" + r)
                        .collect(Collectors.toSet())
        );

        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet())
        );

        return new AuthResponse(token, userResponse);
    }
}
