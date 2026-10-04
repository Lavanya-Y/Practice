package org.example.model;

import org.example.service.NotificationService;

import java.util.List;

public class Event {
    ORDER_EVENTS orderEvent;
    List<NotificationService> defaultNotificationServices;

    public Event(ORDER_EVENTS orderEvent, List<NotificationService> defaultNotificationServices) {
        this.orderEvent = orderEvent;
        this.defaultNotificationServices = defaultNotificationServices;
    }

    public List<NotificationService> getDefaultNotificationServices() {
        return defaultNotificationServices;
    }

    public void setDefaultNotificationServices(List<NotificationService> defaultNotificationServices) {
        this.defaultNotificationServices = defaultNotificationServices;
    }
}
