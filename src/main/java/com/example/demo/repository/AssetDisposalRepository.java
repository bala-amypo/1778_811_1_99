package com.example.demo.repository;

import com.example.demo.entity.AssetDisposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetDisposalRepository
        extends JpaRepository<AssetDisposal, Long> {

    // ✅ ID-based query (Hibernate-safe, test-safe)
    List<AssetDisposal> findByApprovedBy_Id(Long approvedById);
}
