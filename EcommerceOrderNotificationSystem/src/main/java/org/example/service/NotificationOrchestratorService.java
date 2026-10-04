package org.example.service;

import org.example.model.Notification;
import org.example.model.NotificationMessage;
import org.example.model.ORDER_EVENTS;
import org.example.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationOrchestratorService {
    UserService userService;
    Map<String, Notification> notificationMap;

    public void sendNotification(Order order, ORDER_EVENTS orderEvent, NotificationMessage notificationMessage) throws Exception {
        Map<String, List<NotificationService>> notifs=getEventChannelTypes(new ArrayList<>(List.of(order.getCustomerId(),order.getDeliveryId(), order.getSellerId())), orderEvent);
        for (Map.Entry<String, List<NotificationService>> notif: notifs.entrySet()) {
            sendNotifToChannel(notif.getKey(), notif.getValue(), notificationMessage);
            Map<String, Notification> notification = new HashMap<>();
            notification.putIfAbsent(notif.getKey() + orderEvent, new Notification(notif.getKey(), orderEvent, notificationMessage));
        }
    }

    public void replayNotification(String username, ORDER_EVENTS orderEvent) throws Exception {
        NotificationMessage notificationMessage = notificationMap.get(username + orderEvent).getNotificationMessage();
        Map<String, List<NotificationService>> notifs = getEventChannelTypes(new ArrayList<>(List.of(username)), orderEvent);
        for (Map.Entry<String, List<NotificationService>> notif: notifs.entrySet()) {
            sendNotifToChannel(notif.getKey(), notif.getValue(), notificationMessage);
        }
    }

    private static void sendNotifToChannel(String key, List<NotificationService> notificationServices, NotificationMessage notificationMessage) {
        for (NotificationService notificationService: notificationServices) {
            notificationService.send(key, notificationMessage);
        }
    }

    private Map<String, List<NotificationService>> getEventChannelTypes(List<String> userIds, ORDER_EVENTS orderEvent) throws Exception {
        Map<String, List<NotificationService>> notifList = new HashMap<>();
        for (String user: userIds) {
            List<NotificationService> notificationServices = userService.getEventChannelTypes(user, orderEvent);
            notifList.put(user, notificationServices);
        }
        return notifList;
    }
}
