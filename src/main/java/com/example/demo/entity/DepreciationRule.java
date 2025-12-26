package com.example.demo.entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Pattern;

import jakarta.persistence.*;


@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = "ruleName")
)

public class DepreciationRule extends BaseEntity {
    @NotBlank
    private String ruleName;
    @NotBlank
    @Pattern(regexp = "STRAIGHT_LINE|DECLINING_BALANCE")
    private String method;
    @NotBlank
    @Positive
    private int usefulLifeYears;
    @NotBlank
    @NotBlank
    private double salvageValue;

    public String getRuleName() {
        return ruleName;
    }
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public int getUsefulLifeYears() {
        return usefulLifeYears;
    }
    public void setUsefulLifeYears(int usefulLifeYears) {
        this.usefulLifeYears = usefulLifeYears;
    }
    public double getSalvageValue() {
        return salvageValue;
    }
    public void setSalvageValue(double salvageValue) {
        this.salvageValue = salvageValue;
    }

    
}
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
package com.example.demo.entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Pattern;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
package com.example.demo.entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.*;


@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = "vendorName")
)

public class Vendor extends BaseEntity {
    @NotBlank
    private String vendorName;
    @NotBlank
    private String contactEmail;
    public String getVendorName() {
        return vendorName;
    }
    public void setId(Long id) {
    this.id = id;
    }
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    public String getContactEmail() {
        return contactEmail;
    }
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    
}
