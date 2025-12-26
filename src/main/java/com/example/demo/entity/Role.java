package com.example.demo.entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Pattern;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(unique = true)
    @NotBlank
    private String name;

    public Role() {}
    public Role(String name) { this.name = name; }

    public String getName() { return name; }
}
