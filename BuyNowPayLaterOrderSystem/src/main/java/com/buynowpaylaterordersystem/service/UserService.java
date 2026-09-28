package com.buynowpaylaterordersystem.service;

import com.buynowpaylaterordersystem.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    Map<String, User> users;
    Double bnplCreditLimit = 50000.0;

    public UserService() {
        this.users = new HashMap<>();
    }

    public void registerUser(String name, Double bnplCreditInitial) {
        if (users.containsKey(name)) {
            throw new IllegalArgumentException("User already registered.");
        }
        users.put(name, new User(name, bnplCreditInitial, bnplCreditLimit, false));
        System.out.println("User: " + name + " registered.");
    }

    public Double reduceBnplCredits(User user, Double bnplCredit) {
        return user.reduceBnplCredits(bnplCredit);
    }

    public Double addBnplCredits(User user, Double bnplCredit) {
        return user.addBnplCredits(bnplCredit);
    }

    public User getUserDetails(String username) {
        if (users.containsKey(username)) {
            return users.get(username);
        }
        throw new IllegalArgumentException("User not registered.");
    }
}
