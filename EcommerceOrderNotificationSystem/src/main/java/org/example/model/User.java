package org.example.model;

import org.example.service.NotificationService;

import java.util.List;
import java.util.Map;

public class User {
    String username;
    USER_TYPE userType;
    Map<ORDER_EVENTS, List<NotificationService>> eventsChannelMap;

    public User(String username, USER_TYPE userType, Map<ORDER_EVENTS, List<NotificationService>> eventsChannelMap) {
        this.username = username;
        this.userType = userType;
        this.eventsChannelMap = eventsChannelMap;
    }

    public void addEventChannel(ORDER_EVENTS orderEvents, List<NotificationService> notificationServices) {
        if (!eventsChannelMap.containsKey(orderEvents)) {
            eventsChannelMap.putIfAbsent(orderEvents, notificationServices);
        }
    }

    public void removeEventChannel(ORDER_EVENTS orderEvents) {
        eventsChannelMap.remove(orderEvents);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public USER_TYPE getUserType() {
        return userType;
    }

    public void setUserType(USER_TYPE userType) {
        this.userType = userType;
    }

    public Map<ORDER_EVENTS, List<NotificationService>> getEventsChannelMap() {
        return eventsChannelMap;
    }

    public void setEventsChannelMap(Map<ORDER_EVENTS, List<NotificationService>> eventsChannelMap) {
        this.eventsChannelMap = eventsChannelMap;
    }
}
