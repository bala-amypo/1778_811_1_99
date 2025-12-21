package com.example.demo.controller;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disposals")
public class AssetDisposalController{

    private final AssetDisposalService disposalService;

    public AssetDisposalController(AssetDisposalService disposalService) {
        this.disposalService = disposalService;
    }

    /**
     * Request disposal for an asset
     * POST /api/disposals/request/{assetId}
     */
    @PostMapping("/request/{assetId}")
    public AssetDisposal requestDisposal(@PathVariable Long assetId,
                                         @RequestBody AssetDisposal disposal) {
        return disposalService.requestDisposal(assetId, disposal);
    }

    /**
     * Approve disposal (ADMIN only – enforced by security layer)
     * PUT /api/disposals/approve/{disposalId}/{adminId}
     */
    @PutMapping("/approve/{disposalId}/{adminId}")
    public AssetDisposal approveDisposal(@PathVariable Long disposalId,
                                         @PathVariable Long adminId) {
        return disposalService.approveDisposal(disposalId, adminId);
    }
}
