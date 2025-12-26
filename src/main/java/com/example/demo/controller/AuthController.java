// AssetController.java
package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetRepository assetRepository;
    private final VendorRepository vendorRepository;
    private final DepreciationRuleRepository ruleRepository;

    public AssetController(AssetRepository assetRepository,
                           VendorRepository vendorRepository,
                           DepreciationRuleRepository ruleRepository) {
        this.assetRepository = assetRepository;
        this.vendorRepository = vendorRepository;
        this.ruleRepository = ruleRepository;
    }

    @PostMapping("/{vendorId}/{ruleId}")
    public Asset create(@PathVariable Long vendorId,
                        @PathVariable Long ruleId,
                        @Valid @RequestBody Asset asset) {

        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow();
        DepreciationRule rule = ruleRepository.findById(ruleId).orElseThrow();

        asset.setVendor(vendor);
        asset.setDepreciationRule(rule);

        return assetRepository.save(asset);
    }

    @GetMapping
    public List<Asset> getAll() {
        return assetRepository.findAll();
    }

    @GetMapping("/{id}")
    public Asset getById(@PathVariable Long id) {
        return assetRepository.findById(id).orElseThrow();
    }

    @GetMapping("/status/{status}")
    public List<Asset> getByStatus(@PathVariable String status) {
        return assetRepository.findByStatus(status);
    }
}
