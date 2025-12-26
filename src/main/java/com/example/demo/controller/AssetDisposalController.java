package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/disposals")
public class AssetDisposalController {

    private final AssetDisposalRepository disposalRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public AssetDisposalController(
            AssetDisposalRepository disposalRepository,
            AssetRepository assetRepository,
            UserRepository userRepository
    ) {
        this.disposalRepository = disposalRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/request/{assetId}")
    public ResponseEntity<AssetDisposal> request(
            @PathVariable Long assetId,
            @Valid @RequestBody AssetDisposal disposal
    ) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        if (disposal.getDisposalDate().isAfter(LocalDate.now())) {
            throw new BadRequestException("Disposal date cannot be in the future");
        }

        disposal.setAsset(asset);
        AssetDisposal saved = disposalRepository.save(disposal);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/approve/{disposalId}/{userId}")
    public ResponseEntity<AssetDisposal> approve(
            @PathVariable Long disposalId,
            @PathVariable Long userId
    ) {
        AssetDisposal disposal = disposalRepository.findById(disposalId)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal not found"));

        User approver = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        disposal.setApprovedBy(approver);
        disposal.getAsset().setStatus("DISPOSED");

        return ResponseEntity.ok(disposalRepository.save(disposal));
    }
}
