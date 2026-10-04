package org.example.model;

import java.time.LocalDateTime;

public class Order {
    String orderId;
    String customerId;
    String sellerId;
    String deliveryId;
    ORDER_EVENTS orderStatus;
    LocalDateTime timeStamp;

    public Order(String orderId, String customerId, String sellerId, String deliveryId, ORDER_EVENTS orderStatus) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.sellerId = sellerId;
        this.deliveryId = deliveryId;
        this.orderStatus = orderStatus;
        this.timeStamp = LocalDateTime.now();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public ORDER_EVENTS getOrderStatus(ORDER_EVENTS orderEvent) {
        return orderEvent;
    }

    public void setOrderStatus(ORDER_EVENTS orderEvent) {
        this.orderStatus = orderEvent;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
