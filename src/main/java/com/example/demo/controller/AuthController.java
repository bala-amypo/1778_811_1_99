package com.example.demo.controller;
import jakarta.validation.Valid;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;

import java.util.stream.Collectors;

import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @Valid @RequestBody Map<String, String> body) {
        User user = new User();
        user.setEmail(body.get("email"));
        user.setName(body.get("name"));
        user.setPassword(passwordEncoder.encode(body.get("password")));

        Role role = roleRepository.findByName("USER")
                .orElseGet(() -> roleRepository.save(new Role("USER")));
        user.getRoles().add(role);

        return ResponseEntity.ok(userRepository.save(user));
    }

        @PostMapping("/login")
        public ResponseEntity<Map<String, Object>> login(@Valid @Valid @RequestBody Map<String, String> request) {

                String email = request.get("email");
                String password = request.get("password");

                User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new ResourceNotFoundException("Invalid email or password"));

                // 🔐 THIS IS THE CRITICAL CHECK
                if (!passwordEncoder.matches(password, user.getPassword())) {
                        throw new BadRequestException("Invalid email or password");
                }

                Set<String> roles = user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet());

                String token = jwtUtil.generateToken(user.getEmail(), user.getId(), roles);

                Map<String, Object> response = new HashMap<>();
                response.put("token", token);

                return ResponseEntity.ok(response);
        }

}
