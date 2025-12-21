package com.example.demo.service;

import com.example.demo.entity.AssetDisposal;
import org.springframework.lang.NonNull;

public interface AssetDisposalService {

    @NonNull
    AssetDisposal requestDisposal(
            @NonNull Long assetId,
            @NonNull AssetDisposal disposal
    );

    @NonNull
    AssetDisposal approveDisposal(
            @NonNull Long disposalId,
            @NonNull Long adminId
    );
}
