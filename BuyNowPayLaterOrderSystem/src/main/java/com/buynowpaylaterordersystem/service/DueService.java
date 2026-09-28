package com.buynowpaylaterordersystem.service;

import com.buynowpaylaterordersystem.model.DUE_STATUS;
import com.buynowpaylaterordersystem.model.Due;
import com.buynowpaylaterordersystem.model.Order;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DueService {
    public Map<String, Due> dues;
    long DUE_DATE_WINDOW = 30L;

    OrderService orderService;
    UserService userService;

    public DueService(UserService userService) {
        this.dues = new HashMap<>();
        this.userService = userService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void createDue(String orderId, Double orderAmount, DUE_STATUS dueStatus, LocalDateTime curTimeStamp) {
        LocalDateTime dueDate = this.calculateDueDate(curTimeStamp);
        Due due = new Due(orderId, orderAmount, dueStatus, curTimeStamp, dueDate);
        dues.put(orderId, due);
    }

    public void updateDueStatus(String orderId, DUE_STATUS dueStatus) {
        Due due = this.getDue(orderId);
        due.setDueStatus(dueStatus);
    }

    public void viewDues(String username, LocalDateTime dateTime) {
//        System.out.println("DueService in viewDues: " + this);
//        for (Map.Entry<String, Order> entry : orderService.orders.entrySet()) {
//            String orderId = entry.getKey();
//            Order order = entry.getValue();
//
//            System.out.println("Order ID: " + orderId
//                    + ", Order: " + order
//                    + ", OrderId: " + order.getOrderId()
//                    + ", Username: " + order.getUsername());
//        }
//        for (Map.Entry<String, Due> entry : dues.entrySet()) {
//            String orderId = entry.getKey();
//            Due order = entry.getValue();
//
//            System.out.println("Due Order ID: " + orderId
//                    + ", Due OrderId: " + order.getOrderId()
//                    + ", Due Status: " + order.getDueStatus());
//        }
//        System.out.println("username: " + username + ", orders: " + orderService.orders);
        List<Due> filteredDues = dues.values().stream()
                .filter(map -> (map.getDueStatus() == DUE_STATUS.PENDING || map.getDueStatus() == DUE_STATUS.DELAYED))
                .filter(due -> (due.getDueCreatedTs().toLocalDate().isEqual(dateTime.toLocalDate())))
                .filter(due -> {
                    Order order = orderService.getOrders(due.getOrderId());
                    return order != null && username.equals(order.getUsername());
                })
                .sorted(Comparator.comparing(Due::getDueCreatedTs))
                .collect(Collectors.toList());
        for (Due due: filteredDues) {
            System.out.println("View Dues: Order ID: " + due.getOrderId() + ", Amount: " + due.getDueAmount() + ", Status: " + due.getDueStatus() + ", Due Date: " + due.getDueDate());
        }

    }

    public void clearDues(String username, List<String> orderIds, LocalDateTime curTimeStamp) {
        for (String orderId: orderIds) {
            Due due = this.getDue(orderId);
            if (curTimeStamp.isAfter(due.getDueDate())) {
                this.updateDueStatus(orderId, DUE_STATUS.DELAYED);
            } else {
                this.updateDueStatus(orderId, DUE_STATUS.CLEARED);
            }
            userService.addBnplCredits(userService.getUserDetails(username), due.getDueAmount());
        }
    }

    public Due getDue(String orderId) {
        if (dues.containsKey(orderId)) {
            return dues.get(orderId);
        }
        return null;
    }

    private LocalDateTime calculateDueDate(LocalDateTime curTimeStamp) {
        return curTimeStamp.plusDays(DUE_DATE_WINDOW);
    }
}
