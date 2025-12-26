package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Proxy;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = "vendorName")
)
@Proxy(lazy = false)
public class Vendor extends BaseEntity {

    private String vendorName;
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
