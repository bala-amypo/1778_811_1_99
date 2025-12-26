package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    protected LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() { return id; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
