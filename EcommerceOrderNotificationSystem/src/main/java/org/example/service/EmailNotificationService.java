package org.example.service;

import org.example.model.NotificationMessage;

public class EmailNotificationService implements NotificationService{
    @Override
    public void send(String username, NotificationMessage notificationMessage) {
        System.out.println(notificationMessage + " [EMAIL Notification]");
    }
}
