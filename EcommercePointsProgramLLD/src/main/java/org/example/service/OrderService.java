package org.example.service;

import org.example.model.Order;

import java.time.LocalDateTime;
import java.util.*;

public class OrderService {
    Map<String, List<Order>> orders;
    UserService userService;

    public OrderService(UserService userService) {
        this.orders = new HashMap<>();
        this.userService = userService;
    }


    public Order createOrder(Double amount, Double pointsRedeemed, Double pointsEarned) {
        String id = UUID.randomUUID().toString();
        Order order = new Order(id, amount, LocalDateTime.now(), pointsRedeemed, pointsEarned);
        return order;
    }

    public void placeOrder(String name, Order order) {
        this.orders.computeIfAbsent(name, k -> new ArrayList<>()).add(order);
        userService.addOrderCount(name);
    }

    public Double getTotalSpending(String name) {
        Double totalAmount = 0.0;
        for (Order order: this.getOrderDetails(name)) {
            totalAmount += order.getAmount();
        }
        return totalAmount;
    }

    public List<Order> getOrderDetails(String name) {
        return orders.getOrDefault(name, new ArrayList<>());
    }
}
