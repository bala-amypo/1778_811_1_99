package com.example.demo.controller;
import jakarta.validation.Valid;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disposals")
public class AssetDisposalController {

    @Autowired private AssetDisposalRepository disposalRepository;
    @Autowired private AssetRepository assetRepository;
    @Autowired private UserRepository userRepository;

    @PostMapping("/request/{assetId}")
    public AssetDisposal request(@PathVariable Long assetId,
                                 @Valid @Valid @RequestBody AssetDisposal disposal) {

        Asset asset = assetRepository.findById(assetId).orElseThrow();
        disposal.setAsset(asset);
        return disposalRepository.save(disposal);
    }

    @PutMapping("/approve/{disposalId}/{userId}")
    public AssetDisposal approve(@PathVariable Long disposalId,
                                 @PathVariable Long userId) {

        AssetDisposal disposal = disposalRepository.findById(disposalId).orElseThrow();
        User approver = userRepository.findById(userId).orElseThrow();

        disposal.setApprovedBy(approver);
        disposal.getAsset().setStatus("DISPOSED");

        return disposalRepository.save(disposal);
    }
}
