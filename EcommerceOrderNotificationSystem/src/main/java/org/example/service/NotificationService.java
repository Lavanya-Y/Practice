package org.example.service;

import org.example.model.NotificationMessage;

public interface NotificationService {
    public void send(String username, NotificationMessage notificationMessage);
}
