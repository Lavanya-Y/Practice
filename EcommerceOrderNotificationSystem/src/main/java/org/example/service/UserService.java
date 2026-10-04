package org.example.service;

import org.example.model.ORDER_EVENTS;
import org.example.model.USER_TYPE;
import org.example.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    Map<String, User> userMap;
    EventService eventService;

    public void addUser(String username, USER_TYPE userType) throws Exception {
        if (userMap.containsKey(username)) {
            throw new Exception("User already exists");
        }
        User user = new User(username, userType, new HashMap<>());
        userMap.put(username, user);
        eventService.subscribeDefaultEvent(username, userType);
    }

    public User getUserbyName(String username) {
        return userMap.get(username);
    }

    public List<NotificationService> getEventChannelTypes(String username, ORDER_EVENTS orderEvent) throws Exception {
        User user = userMap.get(username);
        if (user==null) {
            throw new Exception("User doesn't exist.");
        }
        return user.getEventsChannelMap().get(orderEvent);
    }
}
