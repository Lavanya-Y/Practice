package com.buynowpaylaterordersystem.service;

import com.buynowpaylaterordersystem.model.User;

public class BnplPaymentStrategy implements PaymentStrategy{
    UserService userService;

    public BnplPaymentStrategy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean pay(String username, Double orderAmount) throws Exception {
        User user = userService.getUserDetails(username);
        this.checkCredits(user, orderAmount);
        Double creditsReduced = userService.reduceBnplCredits(user, orderAmount);
        return creditsReduced>=0;
    }

    private void checkCredits(User user, Double orderAmount) throws Exception {
        if (user.getBnplBlocked()) {
            throw new Exception("User blocked as user does not have enough credits.");
        }
        if (user.getBnplCreditLimit() < orderAmount) {
            throw new Exception("Amount exceeds credit limit.");
        }
        if (user.getBnplCredit() < orderAmount) {
            throw new Exception("User does not have enough credits.");
        }
    }
}
