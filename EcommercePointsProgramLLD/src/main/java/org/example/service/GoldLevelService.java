package org.example.service;

public class GoldLevelService implements LevelService {
    Double maxRedeemPercentage = 0.0;
    Double maxRedeemPoints = 0.0;
    Double maxEarnPercentage = 0.0;
    Double eligibleMinPoints = 0.0;
    Double eligibleMaxPoints = 0.0;

    public GoldLevelService() {
        this.maxRedeemPercentage = 15.0;
        this.maxRedeemPoints = 1000.0;
        this.maxEarnPercentage = 15.0;
        this.eligibleMinPoints = 1000.0;
        this.eligibleMaxPoints = 99999.0;
    }

    public GoldLevelService(Double maxRedeemPercentage, Double maxRedeemPoints, Double maxEarnPercentage, Double eligibleMinPoints, Double eligibleMaxPoints) {
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
