package com.buynowpaylaterordersystem.service;

import com.buynowpaylaterordersystem.model.PAYMENT_TYPE;

public class PaymentService {
    UserService userService;

    public PaymentService(UserService userService) {
        this.userService = userService;
    }

    public PaymentStrategy assignPaymentStrategy(PAYMENT_TYPE paymentType) {
        return switch (paymentType) {
            case BNPL -> new BnplPaymentStrategy(userService);
            case PREPAID -> new PrepaidPaymentStartegy();
        };
    }
}
