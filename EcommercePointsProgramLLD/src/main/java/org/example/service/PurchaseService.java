package org.example.service;

import org.example.model.User;

public class PurchaseService {
    OrderService orderService;
    UserService userService;
    RedeemPointsService redeemPointsService;
    EarnPointsService earnPointsService;

    public PurchaseService(UserService userService, RedeemPointsService redeemPointsService, EarnPointsService earnPointsService, OrderService orderService) {
        this.userService = userService;
        this.redeemPointsService = redeemPointsService;
        this.earnPointsService = earnPointsService;
        this.orderService = orderService;
    }

    public void purchase(String name, Double amount, Double pointsToRedeem) {
        try {
            User userOld = userService.getUserDetails(name);
            LevelService currentLevel = userOld.getLevelService();

            Double pointsRedeemed = redeemPointsService.redeemPoints(name, pointsToRedeem);
            Double discountCalculated = redeemPointsService.calculateDiscount(userOld, amount-pointsRedeemed);
            Double pointsEarned = earnPointsService.earnPoints(name, amount-pointsRedeemed-discountCalculated, currentLevel);
            orderService.placeOrder(name, orderService.createOrder(amount-pointsRedeemed-discountCalculated, pointsRedeemed, pointsEarned));
            User user = userService.getUserDetails(name);
            System.out.println("Name: " + name + ". Total Points: " + user.getPoints() + ". Level: " + user.getLevelService());
            System.out.println("Name: " + name + ". Points Redeemed: " + pointsRedeemed + ". Points Earned: " + pointsEarned + ". Discount Applied: " + discountCalculated);
            System.out.println("----------------------");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
