package com.example.demo.entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.*;
import java.time.LocalDate;
import org.hibernate.annotations.Proxy;
@Proxy(lazy = false)
@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = "assetTag")
)

public class Asset extends BaseEntity {
    @NotBlank
    private String assetTag;

    @NotBlank
    private String assetName;

    @NotNull
    @Positive
    private LocalDate purchaseDate;

    @Positive
    private double purchaseCost;

    private String status = "ACTIVE";

    @ManyToOne
    private Vendor vendor;

    @NotBlank
    @ManyToOne
    private DepreciationRule depreciationRule;

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public DepreciationRule getDepreciationRule() {
        return depreciationRule;
    }

    public void setDepreciationRule(DepreciationRule depreciationRule) {
        this.depreciationRule = depreciationRule;
    }

    
}
