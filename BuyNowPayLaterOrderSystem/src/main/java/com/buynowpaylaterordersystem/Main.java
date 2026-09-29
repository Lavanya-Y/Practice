package com.buynowpaylaterordersystem;

import com.buynowpaylaterordersystem.model.PAYMENT_TYPE;
import com.buynowpaylaterordersystem.model.Product;
import com.buynowpaylaterordersystem.service.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() throws Exception {
        UserService userService = new UserService();
        PaymentService paymentService = new PaymentService(userService);
        DueService dueService = new DueService(userService);
        InventoryService inventoryService = new InventoryService();

        OrderService orderService = new OrderService(paymentService, dueService, userService, inventoryService);
        userService.registerUser("user1", 5000.0);
        userService.registerUser("user2", 5000.0);


        inventoryService.seedInventory(List.of(
                new Product("headphones", 2000.0, 50),
                new Product("keyboard", 1500.0, 20),
                new Product("mouse", 300.0, 20),
                new Product("phone", 30000.0, 20),
                new Product("bottle", 1500.0, 20),
                new Product("bag", 3000.0, 2)
        ));

        inventoryService.viewInventory();
        System.out.println();
        orderService.buy("orderId1", "user1", Map.of("bag", 1, "headphones", 1), PAYMENT_TYPE.BNPL);
        inventoryService.viewInventory();
        System.out.println();
        orderService.buy("orderId2", "user2", Map.of("mouse", 1, "headphones", 1), PAYMENT_TYPE.PREPAID);
        orderService.buy("orderId3", "user2", Map.of("mouse", 1, "headphones", 1), PAYMENT_TYPE.BNPL);
        orderService.buy("orderId4", "user2", Map.of("mouse", 1, "headphones", 1), PAYMENT_TYPE.BNPL);
        System.out.println();
        dueService.viewDues("user1", LocalDateTime.now());
        System.out.println();
        dueService.viewDues("user2", LocalDateTime.now());
        dueService.clearDues("user2", List.of("orderId3"), LocalDateTime.now().plusDays(50));
        System.out.println();
        dueService.viewDues("user2", LocalDateTime.now());
        orderService.buy("orderId1", "user1", Map.of("bag", 1, "headphones", 5), PAYMENT_TYPE.BNPL);
        System.out.println();
        orderService.orderStatus("user1");
        orderService.orderStatus("user2");

    }
}
