package org.example.model;

public class Notification {
    String userOrderEventId;
    String username;
    ORDER_EVENTS orderEvent;
    NotificationMessage notificationMessage;

    public Notification(String username, ORDER_EVENTS orderEvent, NotificationMessage notificationMessage) {
        this.userOrderEventId = username + orderEvent;
        this.username = username;
        this.orderEvent = orderEvent;
        this.notificationMessage = notificationMessage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ORDER_EVENTS getOrderEvent() {
        return orderEvent;
    }

    public void setOrderEvent(ORDER_EVENTS orderEvent) {
        this.orderEvent = orderEvent;
    }

    public NotificationMessage getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(NotificationMessage notificationMessage) {
        this.notificationMessage = notificationMessage;
    }
}
