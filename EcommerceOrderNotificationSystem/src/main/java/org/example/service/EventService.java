package org.example.service;

import org.example.model.ORDER_EVENTS;
import org.example.model.USER_TYPE;
import org.example.model.User;

import java.util.List;
import java.util.Map;

public class EventService {
    Map<USER_TYPE, List<ORDER_EVENTS>> userToEvents;
    UserService userService;
    NotificationChannelFactory notificationChannelFactory;

    public void setDefaultUserToEvents() {
        userToEvents.putIfAbsent(USER_TYPE.CUSTOMER, List.of(ORDER_EVENTS.ORDER_PLACED, ORDER_EVENTS.ORDER_SHIPPED, ORDER_EVENTS.ORDER_DELIVERED));
        userToEvents.putIfAbsent(USER_TYPE.SELLER, List.of(ORDER_EVENTS.ORDER_PLACED));
        userToEvents.putIfAbsent(USER_TYPE.DELIVERY_PARTNER, List.of(ORDER_EVENTS.ORDER_SHIPPED));
    }

    public List<ORDER_EVENTS> getUserTypeEvent(USER_TYPE userType) {
        return userToEvents.get(userType);
    }

    public void subscribeEvent(String username, ORDER_EVENTS orderEvent) {
        User user = userService.getUserbyName(username);
        user.addEventChannel(orderEvent, notificationChannelFactory.getAllNotificationChannels());
    }

    public void unsubscribeEvent(String username, ORDER_EVENTS orderEvent) {
        User user = userService.getUserbyName(username);
        user.removeEventChannel(orderEvent);
    }

    public void subscribeDefaultEvent(String username, USER_TYPE userType) {
        for (ORDER_EVENTS orderEvents: getUserTypeEvent(userType)) {
            subscribeEvent(username, orderEvents);
        }
    }
}
