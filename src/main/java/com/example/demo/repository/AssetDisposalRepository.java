package com.example.demo.repository;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AssetDisposalRepository
        extends JpaRepository<AssetDisposal, Long> {

    @Transactional(readOnly = false) // 🔴 FORCE FLUSH
    @Query("SELECT d FROM AssetDisposal d WHERE d.approvedBy = :user")
    List<AssetDisposal> findByApprovedBy(@Param("user") User user);
}
