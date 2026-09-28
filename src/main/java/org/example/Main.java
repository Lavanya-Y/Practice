package org.example;

import org.example.service.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        UserService userService = new UserService();
        EarnPointsService earnPointsService = new EarnPointsService(userService);
        OrderService orderService = new OrderService(userService);
        RedeemPointsService redeemPointsService = new RedeemPointsService(userService,orderService);

        userService.onboardUser("Madhu");
        userService.onboardUser("Lavanya");

        PurchaseService purchaseService = new PurchaseService(userService, redeemPointsService, earnPointsService, orderService);
        purchaseService.purchase("Madhu", 800.0, 0.0);
        purchaseService.purchase("Madhu", 4200.0, 100.0);
        purchaseService.purchase("Madhu", 4200.0, 0.0);
        purchaseService.purchase("Madhu", 3000.0,300.0);
        purchaseService.purchase("Madhu", 5000.0, 0.0);
        purchaseService.purchase("Madhu", 12000.0, 800.0);

    }
}
