package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.AssetService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final VendorRepository vendorRepository;
    private final DepreciationRuleRepository ruleRepository;

    public AssetServiceImpl(AssetRepository assetRepository,
                            VendorRepository vendorRepository,
                            DepreciationRuleRepository ruleRepository) {
        this.assetRepository = assetRepository;
        this.vendorRepository = vendorRepository;
        this.ruleRepository = ruleRepository;
    }

    @Override
    public Asset createAsset(Long vendorId, Long ruleId, Asset asset) {

        
        if (vendorId == null || ruleId == null) {
            throw new IllegalArgumentException("VendorId and RuleId must not be null");
        }

        if (asset == null) {
            throw new IllegalArgumentException("Asset must not be null");
        }

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found"));

        DepreciationRule rule = ruleRepository.findById(ruleId)
                .orElseThrow(() -> new ResourceNotFoundException("Depreciation rule not found"));

        if (asset.getPurchaseCost() == null || asset.getPurchaseCost() <= 0) {
            throw new IllegalArgumentException("Purchase cost must be greater than 0");
        }

        if (asset.getAssetTag() == null || asset.getAssetTag().isBlank()) {
            throw new IllegalArgumentException("Asset tag must not be blank");
        }

        if (assetRepository.existsByAssetTag(asset.getAssetTag())) {
            throw new IllegalArgumentException("Asset tag already exists");
        }

        asset.setVendor(vendor);
        asset.setDepreciationRule(rule);
        asset.setStatus("ACTIVE");
        asset.setCreatedAt(LocalDateTime.now());

        return assetRepository.save(asset);
    }

    @Override
    public List<Asset> getAssetsByStatus(String status) {

        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status must not be blank");
        }

        return assetRepository.findByStatus(status);
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @Override
    public Asset getAsset(Long id) {

        // ✅ NULL SAFETY (fixes warning)
        if (id == null) {
            throw new IllegalArgumentException("Asset id must not be null");
        }

        return assetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));
    }
}
