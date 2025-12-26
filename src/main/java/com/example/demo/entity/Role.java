package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(unique = true)
    private String name;

    public Role() {}
    public Role(String name) { this.name = name; }

    public String getName() { return name; }
}
