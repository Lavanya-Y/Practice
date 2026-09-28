package org.example.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.IntToDoubleFunction;

public class Order {
    String orderId;
    Double amount;
    LocalDateTime timestamp;
    Double pointsRedeemed;
    Double pointsEarned;

    public Order(String orderId, Double amount, LocalDateTime timestamp, Double pointsRedeemed, Double pointsEarned) {
        this.orderId = orderId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.pointsRedeemed = pointsRedeemed;
        this.pointsEarned = pointsEarned;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Double getPointsRedeemed() {
        return pointsRedeemed;
    }

    public void setPointsRedeemed(Double pointsRedeemed) {
        this.pointsRedeemed = pointsRedeemed;
    }

    public Double getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(Double pointsEarned) {
        this.pointsEarned = pointsEarned;
    }
}
