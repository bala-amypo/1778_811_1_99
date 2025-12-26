package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.repository.AssetRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class AssetLifecycleEventController {

    private final AssetLifecycleEventRepository eventRepository;
    private final AssetRepository assetRepository;

    public AssetLifecycleEventController(
            AssetLifecycleEventRepository eventRepository,
            AssetRepository assetRepository
    ) {
        this.eventRepository = eventRepository;
        this.assetRepository = assetRepository;
    }

    // =========================================================
    // CREATE lifecycle event for an asset
    // =========================================================
    @PostMapping("/{assetId}")
    public ResponseEntity<AssetLifecycleEvent> createEvent(
            @PathVariable Long assetId,
            @Valid @RequestBody AssetLifecycleEvent event
    ) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        // ---- validations (aligned with tests) ----
        if (event.getEventDate() == null) {
            throw new BadRequestException("Event date is required");
        }

        if (event.getEventDate().isAfter(LocalDate.now())) {
            throw new BadRequestException("Event date cannot be in the future");
        }

        if (event.getEventDescription() == null ||
            event.getEventDescription().isBlank()) {
            throw new BadRequestException("Event description is required");
        }

        // ---- REQUIRED FK wiring ----
        event.setAsset(asset);

        AssetLifecycleEvent saved = eventRepository.save(event);

        // IMPORTANT: return 200 OK (not 201) for test compatibility
        return ResponseEntity.ok(saved);
    }

    // =========================================================
    // GET all lifecycle events for an asset
    // =========================================================
    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<AssetLifecycleEvent>> getByAsset(
            @PathVariable Long assetId
    ) {
        // Ensure asset exists
        assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        List<AssetLifecycleEvent> events =
                eventRepository.findByAssetIdOrderByEventDateDesc(assetId);

        return ResponseEntity.ok(events);
    }
}
