package com.example.demo.repository;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetDisposalRepository
        extends JpaRepository<AssetDisposal, Long> {

    // ✅ Spring Data derives the query correctly
    List<AssetDisposal> findByApprovedBy(User approvedBy);

}
