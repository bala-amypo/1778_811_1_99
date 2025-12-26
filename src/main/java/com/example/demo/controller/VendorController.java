package com.example.demo.controller;
import jakarta.validation.Valid;

import com.example.demo.entity.Vendor;
import com.example.demo.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    @Autowired
    private VendorRepository vendorRepository;

    @PostMapping
    public Vendor create(@Valid @RequestBody Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @GetMapping
    public List<Vendor> getAll() {
        return vendorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Vendor getById(@PathVariable Long id) {
        return vendorRepository.findById(id).orElseThrow();
    }
}
