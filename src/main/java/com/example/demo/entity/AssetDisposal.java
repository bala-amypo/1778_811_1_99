// =======================
// AssetDisposal.java
// =======================
package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

@Entity
@Table(name = "asset_disposal")
public class AssetDisposal {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(
        name = "asset_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "FK_asset_disposal_asset")
    )
    private Asset asset;

    @ManyToOne(optional = false)
    @JoinColumn(name = "approved_by_id")
    private User approvedBy;


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
