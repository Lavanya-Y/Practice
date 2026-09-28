package org.example.service;

public class RedeemPointsService {
    UserService userService;
    OrderService orderService;

    public RedeemPointsService(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    public Double redeemPoints(String name, Double pointsToRedeem) {
        if (checkIsRedeemable(name, pointsToRedeem)) {
            return userService.removePoints(name, pointsToRedeem);
        } else {
            throw new IllegalArgumentException("Purchase failed. Not enough points to redeem.");
        }
    }

    private boolean checkIsRedeemable(String name, Double pointsToRedeem) {
        User user1 = userService.getUserDetails(name);
        LevelService level = user1.getLevelService();
        return pointsToRedeem <= level.getMaxRedeemPoints() && pointsToRedeem<= user1.getPoints();
    }

    public Double calculateDiscount(User user, Double amount) {
        if ((user.getOrderCount() > 3) && calculateTotalSpending(user.getName())>10000.0) {
            user.setOrderCount(1);
            return Math.min(amount * 0.12, 5000.0);
        } else if (user.getOrderCount() > 3) {
            user.setOrderCount(1);
            return Math.min(amount * 0.05, 5000.0);
        } else if (calculateTotalSpending(user.getName())>10000.0) {
            return Math.min(amount * 0.1, 5000.0);
        }
        return 0.0;
    }

    private Double calculateTotalSpending(String name) {
        return orderService.getTotalSpending(name);
    }
}
