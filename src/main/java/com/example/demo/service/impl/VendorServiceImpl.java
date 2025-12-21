package com.example.demo.service.impl;

import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.regex.Pattern;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {

        if (vendorRepository.existsByVendorName(vendor.getVendorName())) {
            throw new IllegalArgumentException("Vendor name already exists");
        }

        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", vendor.getContactEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }
        
        
        return vendorRepository.save(vendor);
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }
}
