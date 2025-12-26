package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = "ruleName")
)
public class DepreciationRule extends BaseEntity {

    private String ruleName;
    private String method;
    private int usefulLifeYears;
    private double salvageValue;
    public String getRuleName() {
        return ruleName;
    }
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public int getUsefulLifeYears() {
        return usefulLifeYears;
    }
    public void setUsefulLifeYears(int usefulLifeYears) {
        this.usefulLifeYears = usefulLifeYears;
    }
    public double getSalvageValue() {
        return salvageValue;
    }
    public void setSalvageValue(double salvageValue) {
        this.salvageValue = salvageValue;
    }

    
}
