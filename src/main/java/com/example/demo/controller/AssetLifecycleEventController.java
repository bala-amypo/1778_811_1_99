package com.example.demo.controller;
import jakarta.validation.Valid;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class AssetLifecycleEventController {

    @Autowired
    private AssetLifecycleEventRepository eventRepository;

    @Autowired
    private AssetRepository assetRepository;

    @PostMapping("/{assetId}")
    public AssetLifecycleEvent create(@PathVariable Long assetId,
                                      @Valid @Valid @RequestBody AssetLifecycleEvent event) {

        Asset asset = assetRepository.findById(assetId).orElseThrow();
        event.setAsset(asset);
        return eventRepository.save(event);
    }

    @GetMapping("/asset/{assetId}")
    public List<AssetLifecycleEvent> getByAsset(@PathVariable Long assetId) {
        return eventRepository.findByAssetIdOrderByEventDateDesc(assetId);
    }
}
