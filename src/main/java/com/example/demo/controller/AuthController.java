package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        // Dummy implementation
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        // Dummy implementation
        AuthResponse response = new AuthResponse();
        response.setToken("dummy-jwt-token");
        response.setUserId(1L);
        response.setEmail(request.getEmail());
        response.setRoles(java.util.List.of("USER"));

        return ResponseEntity.ok(response);
    }
}
