package org.example.service;

public class EarnPointsService {
    UserService userService;

    public EarnPointsService(UserService userService) {
        this.userService = userService;
    }

    public Double earnPoints(String name, Double amount, LevelService levelService) {
        Double points = this.calculatePoints(amount, levelService.getMaxEarnPercentage());
        return userService.addPoints(name, points);
    }

    private Double calculatePoints(Double amount, Double percentageEarnPoints) {
        return (amount/100)*(percentageEarnPoints);
    }
}
