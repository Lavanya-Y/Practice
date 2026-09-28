package org.example.service;

import org.example.model.User;

import java.util.*;

public class UserService {
    Map<String, User> users;

    public UserService() {
        this.users = new HashMap<>();
    }

    public void onboardUser(String name) {
        users.put(name, createUser((name)));
//        System.out.println(name + " Users: " + users.size());
    }

    public User createUser(String name) {
        if (users.containsKey(name)) {
            throw new IllegalArgumentException("User already exists with this name.");
        }
        String id = UUID.randomUUID().toString();
        User user1 = new User(id, name, 0, 0.0, new BronzeLevelService(5.0,200.0,10.0, 0.0, 499.0));
        return user1;
    }

    public User getUserDetails(String name) {
        if (users.containsKey(name)) {
            User curUser = users.get(name);
            return curUser;
        } else {
            throw new IllegalArgumentException("User doesn't exist with this name.");
        }
    }

    public Double addPoints(String name, Double points) {
        User user1 = this.getUserDetails(name);
        return user1.addPoints(points);
    }

    public Double removePoints(String name, Double points) {
        User user1 = this.getUserDetails(name);
        return user1.removePoints(points);
    }

    public Integer addOrderCount(String name) {
        User user1 = this.getUserDetails(name);
        return user1.addOrderCount();
    }
}
