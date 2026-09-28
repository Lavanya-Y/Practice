package com.buynowpaylaterordersystem.service;

public class PrepaidPaymentStartegy implements PaymentStrategy{

    @Override
    public boolean pay(String username, Double orderAmount) {
        return true;
    }
}
