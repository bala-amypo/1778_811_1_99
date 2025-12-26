package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = "vendorName")
)
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
