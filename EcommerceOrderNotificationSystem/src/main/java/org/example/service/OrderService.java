package org.example.service;

import org.example.model.NOTIFICATION_MESSAGE_TYPE;
import org.example.model.NotificationMessage;
import org.example.model.ORDER_EVENTS;
import org.example.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderService {
    Map<String, List<Order>> orderMap;
    NotificationOrchestratorService notificationOrchService;

    public void placeOrder(String orderId, String customerId, String sellerId, String deliveryId) throws Exception {
        Order order = new Order(orderId, customerId, sellerId, deliveryId, ORDER_EVENTS.ORDER_PLACED);
        orderMap.computeIfAbsent(customerId, user -> new ArrayList<>()).add(order);
        notificationOrchService.sendNotification(order, ORDER_EVENTS.ORDER_PLACED, new NotificationMessage("Order placed.", orderId, NOTIFICATION_MESSAGE_TYPE.NOTIFICATION));
    }

    public void shipOrder(String orderId) throws Exception {
        Optional<Order> order = getOrderById(orderId);
        if (order.isPresent()) {
            Order ord = order.get();
            ord.setOrderStatus(ORDER_EVENTS.ORDER_SHIPPED);
            notificationOrchService.sendNotification(ord, ORDER_EVENTS.ORDER_SHIPPED, new NotificationMessage("Order shipped.", orderId, NOTIFICATION_MESSAGE_TYPE.NOTIFICATION));
        } else throw new Exception("Invalid order.");
    }

    public void deliverOrder(String orderId) throws Exception {
        Optional<Order> order = getOrderById(orderId);
        if (order.isPresent()) {
            Order ord = order.get();
            ord.setOrderStatus(ORDER_EVENTS.ORDER_DELIVERED);
            notificationOrchService.sendNotification(ord, ORDER_EVENTS.ORDER_DELIVERED, new NotificationMessage("Order delivered.", orderId, NOTIFICATION_MESSAGE_TYPE.NOTIFICATION));
        } else throw new Exception("Invalid order.");
    }

    private Optional<Order> getOrderById(String orderId) {
        return orderMap.values().stream()
                .flatMap(List::stream)
                .filter(lambda -> orderId.equals(lambda.getOrderId()))
                .findFirst();
    }
}
