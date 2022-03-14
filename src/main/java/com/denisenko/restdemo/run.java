package com.denisenko.restdemo;

import com.denisenko.restdemo.controller.EventController;
import com.denisenko.restdemo.controller.FileController;
import com.denisenko.restdemo.controller.UserController;
import com.denisenko.restdemo.model.Event;
import com.denisenko.restdemo.model.File;
import com.denisenko.restdemo.model.User;
import com.denisenko.restdemo.utils.EventAdapter;
import com.denisenko.restdemo.utils.FileAdapter;
import com.denisenko.restdemo.utils.HibernateProxyTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class run {
    public static void main(String[] args) {
        UserController userController = new UserController();
        User user = userController.getByIdUser(1);
        System.out.println(user);
    }
}
