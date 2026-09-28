package org.example.service;

public class BronzeLevelService implements LevelService {
    Double maxRedeemPercentage = 0.0;
    Double maxRedeemPoints = 0.0;
    Double maxEarnPercentage = 0.0;
    Double eligibleMinPoints = 0.0;
    Double eligibleMaxPoints = 0.0;

    public BronzeLevelService() {
        this.maxRedeemPercentage = 5.0;
        this.maxRedeemPoints = 200.0;
        this.maxEarnPercentage = 10.0;
        this.eligibleMinPoints = 0.0;
        this.eligibleMaxPoints = 499.0;
    }

    public BronzeLevelService(Double maxRedeemPercentage,Double maxRedeemPoints,Double maxEarnPercentage,Double eligibleMinPoints,Double eligibleMaxPoints) {
        this.maxRedeemPercentage = maxRedeemPercentage;
        this.maxRedeemPoints = maxRedeemPoints;
        this.maxEarnPercentage = maxEarnPercentage;
        this.eligibleMinPoints = eligibleMinPoints;
        this.eligibleMaxPoints = eligibleMaxPoints;
    }

    @Override
    public Double getMaxRedeemPercentage() {
        return maxRedeemPercentage;
    }

    @Override
    public Double getMaxRedeemPoints() {
        return maxRedeemPoints;
    }

    @Override
    public Double getMaxEarnPercentage() {
        return maxEarnPercentage;
    }

    @Override
    public Double getEligibleMinPoints() {
        return eligibleMinPoints;
    }

    @Override
    public Double getEligibleMaxPoints() {
        return eligibleMaxPoints;
    }
}
