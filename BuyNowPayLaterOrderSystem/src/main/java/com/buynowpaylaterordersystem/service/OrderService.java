package com.buynowpaylaterordersystem.service;

import com.buynowpaylaterordersystem.model.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    Map<String, Order> orders;
    PaymentService paymentService;
    DueService dueService;
    UserService userService;
    InventoryService inventoryService;

    public OrderService(PaymentService paymentService, DueService dueService, UserService userService, InventoryService inventoryService) {
        this.orders = new HashMap<>();
        this.paymentService = paymentService;
        this.dueService = dueService;
        this.userService = userService;
        this.inventoryService = inventoryService;
    }

    public void buy(String orderId, String username, Map<String, Integer> itemProducts, PAYMENT_TYPE paymentType) throws Exception {
        try {
            List<Item> items = inventoryService.createItemList(itemProducts);
            Double orderAmount = this.calculateAmount(items);
            LocalDateTime curTimeStamp = LocalDateTime.now();

            inventoryService.checkAvailability(items);
            PaymentStrategy paymentStrategy = paymentService.assignPaymentStrategy(paymentType);
            dueService.setOrderService(this);
            dueService.createDue(orderId, orderAmount, DUE_STATUS.PENDING,curTimeStamp);
            if (paymentType.equals(PAYMENT_TYPE.PREPAID)) {
                dueService.updateDueStatus(orderId, DUE_STATUS.CLEARED);
            }
            boolean paid = paymentStrategy.pay(username, orderAmount);

            if (paid) {
                Order order = new Order(orderId, username, orderAmount, items, paymentType, curTimeStamp);
                orders.put(orderId, order);
                System.out.println("Order successful for username: " + username + ", orderId: " + orderId + ", orderAmount: " + orderAmount + ", paymentType: " + paymentType);
            } else {
                System.out.println("Order unsuccessful for username: " + username + ", orderId: " + orderId + ", orderAmount: " + orderAmount + ", paymentType: " + paymentType);
            }
        } catch (Exception e) {
            System.out.println("Order unsuccessful due to: " + e.getMessage() + ", for username: " + username + ", orderId: " + orderId + ", paymentType: " + paymentType);
        }

    }

    public void orderStatus(String username) {
        User user = userService.getUserDetails(username);
        System.out.println("Username: " + username + ", Available Credits: " + user.getBnplCredit() + ", Credit Limit: " + user.getBnplCreditLimit());
        List<Order> filteredOrders = orders.values().stream()
                .filter(order -> order.getUsername().equals(username))
                .toList();

        for (Order order: filteredOrders) {
            DUE_STATUS dueStatus = dueService.getDue(order.getOrderId()).getDueStatus();
            System.out.println("Order ID: " + order.getOrderId() + ", Amount: " + order.getTotalAmount() + ", Status: " + dueStatus);
        }
    }

    public Order getOrders(String orderId) {
        return orders.get(orderId);
    }

    private Double calculateAmount(List<Item> items) {
        Double amount = 0.0;
        for (Item item: items) {
            amount += item.getProduct().getPrice() * item.getQuantity();
        }
        return amount;
    }
}
