package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

@Entity
@Table(name = "asset_disposal")
public class AssetDisposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String disposalMethod;

    @PositiveOrZero
    @Column(nullable = false)
    private double disposalValue;

    @NotNull
    @Column(nullable = false)
    private LocalDate disposalDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "asset_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_asset_disposal_asset")
    )
    @JsonIgnore
    private Asset asset;

    @ManyToOne(optional = false)
    @JoinColumn(name = "approved_by_id", nullable = false)
    private User approvedBy;

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public String getDisposalMethod() {
        return disposalMethod;
    }

    public void setDisposalMethod(String disposalMethod) {
        this.disposalMethod = disposalMethod;
    }

    public double getDisposalValue() {
        return disposalValue;
    }

    public void setDisposalValue(double disposalValue) {
        this.disposalValue = disposalValue;
    }

    public LocalDate getDisposalDate() {
        return disposalDate;
    }

    public void setDisposalDate(LocalDate disposalDate) {
        this.disposalDate = disposalDate;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }
}
