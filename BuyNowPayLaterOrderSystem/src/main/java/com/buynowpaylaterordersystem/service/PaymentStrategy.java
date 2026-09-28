package com.buynowpaylaterordersystem.service;

public interface PaymentStrategy {
    boolean pay(String username, Double orderAmount) throws Exception;
}
