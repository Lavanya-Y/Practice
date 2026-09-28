package com.buynowpaylaterordersystem.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    String orderId;
    String username;
    Double totalAmount;
    List<Item> items;
    PAYMENT_TYPE paymentType;
    LocalDateTime timestamp;

    public Order(String orderId, String username, Double totalAmount, List<Item> items, PAYMENT_TYPE paymentType, LocalDateTime timestamp) {
        this.orderId = orderId;
        this.username = username;
        this.totalAmount = totalAmount;
        this.items = items;
        this.paymentType = paymentType;
        this.timestamp = timestamp;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public PAYMENT_TYPE getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PAYMENT_TYPE paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
