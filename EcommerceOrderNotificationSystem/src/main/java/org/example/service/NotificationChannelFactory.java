package org.example.service;

import org.example.model.NOTIFICATION_CHANNEL;

import java.util.List;

public class NotificationChannelFactory {
    public NotificationService assignNotificationService(NOTIFICATION_CHANNEL notificationChannel) {
        return switch (notificationChannel) {
            case EMAIL -> new EmailNotificationService();
            case SMS -> new EmailNotificationService();
            case PUSH -> new EmailNotificationService();
        };
    }

    public List<NotificationService> getAllNotificationChannels() {
        return List.of(new EmailNotificationService());
    }
}
