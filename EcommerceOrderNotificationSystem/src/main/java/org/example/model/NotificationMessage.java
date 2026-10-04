package org.example.model;

import java.time.LocalDateTime;

public class NotificationMessage {
    String message;
    String orderId;
    NOTIFICATION_MESSAGE_TYPE notificationMessageType;

    public NotificationMessage(String message, String orderId, NOTIFICATION_MESSAGE_TYPE notificationMessageType) {
        this.message = message;
        this.orderId = orderId;
        this.notificationMessageType = notificationMessageType;
    }

    public String getMessage() {
        return "[" + LocalDateTime.now() + "]" +
                "[" + notificationMessageType + "]" +
                "[" + orderId + "]" +
                "[" + message + "]";
    }
}
