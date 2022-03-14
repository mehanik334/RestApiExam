package com.denisenko.restdemo.controller;

import com.denisenko.restdemo.model.User;
import com.denisenko.restdemo.service.UserService;

import java.util.List;

public class UserController {

    private UserService userService;

    public UserController() {
        userService = new UserService();
    }

    public User getByIdUser(Integer id) {
        return userService.getById(id);
    }

    public User saveUser(User user) {
        return userService.save(user);
    }

    public boolean deleteUserById(Integer id) {
        return userService.delete(id);
    }

    public List<User> getAllUsers() {
        return userService.getAll();
    }

    public User updateUser(User user) {
        return userService.update(user);
    }
}
