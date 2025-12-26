package com.example.demo.service.impl;

import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.repository.AssetLifecycleEventRepository;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetLifecycleEventServiceImpl implements AssetLifecycleEventService {

    private final AssetLifecycleEventRepository eventRepository;

    public AssetLifecycleEventServiceImpl(AssetLifecycleEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public AssetLifecycleEvent logEvent(AssetLifecycleEvent event) {
        return eventRepository.save(event);
    }

    @Override
    public List<AssetLifecycleEvent> getEventsForAsset(Long assetId) {
        return eventRepository.findByAssetIdOrderByEventDateDesc(assetId);
    }
}
