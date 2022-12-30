package com.synergy.challenge6.service;

import com.synergy.challenge6.model.User;

import java.util.Map;

public interface UserService {
    Map createUser(User user);
    Map updateUser(long userId, User user);
    Map deleteUser(long userId);

    Map getAll();

    Map login(String email, String password);
}
