package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

@Entity
public class AssetDisposal extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String disposalMethod;

    @PositiveOrZero
    @Column(nullable = false)
    private double disposalValue;

    @NotNull
    @Column(nullable = false)
    private LocalDate disposalDate;

    @NotNull
    @ManyToOne(optional = false)
    private Asset asset;

    @NotNull
    @ManyToOne(optional = false)
    private User approvedBy;

    // getters & setters

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
