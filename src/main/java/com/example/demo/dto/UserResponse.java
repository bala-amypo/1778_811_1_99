package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private Set<String> roles;

    public UserResponse(Long id, String name, String email,
                        LocalDateTime createdAt, Set<String> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.roles = roles;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Set<String> getRoles() { return roles; }
}
