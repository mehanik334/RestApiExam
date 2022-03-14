package com.denisenko.restdemo.servlets;

import com.denisenko.restdemo.controller.UserController;
import com.denisenko.restdemo.model.Event;
import com.denisenko.restdemo.model.User;
import com.denisenko.restdemo.utils.EventAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "userServlet", value = "/user")
public class UserServlet extends HttpServlet {
    private Gson gson;
    private UserController userController;

    public void init() {
        userController = new UserController();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Event.class, new EventAdapter());
        gson = gsonBuilder.create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        PrintWriter printWriter = resp.getWriter();

        switch (action) {
            case "showAll" -> printWriter.println(gson.toJson(userController.getAllUsers()));
            case "getById" -> {
                int id = Integer.parseInt(req.getParameter("id"));
                printWriter.println(gson.toJson(userController.getByIdUser(id)));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String password = req.getParameter("password");

        User newUser = new User(firstName, lastName, password);
        User savedUser = userController.saveUser(newUser);
        printWriter.println(gson.toJson(savedUser));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();

        int idUpdateUser = Integer.parseInt(req.getParameter("id"));
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String password = req.getParameter("password");

        User updateUser = new User(idUpdateUser,firstName,lastName,password);
        printWriter.println(gson.toJson(userController.updateUser(updateUser)));

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));

        printWriter.println(gson.toJson(userController.deleteUserById(id)));
    }
}
