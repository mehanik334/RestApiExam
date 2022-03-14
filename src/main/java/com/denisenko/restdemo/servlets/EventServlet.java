package com.denisenko.restdemo.servlets;

import com.denisenko.restdemo.controller.EventController;
import com.denisenko.restdemo.controller.FileController;
import com.denisenko.restdemo.controller.UserController;
import com.denisenko.restdemo.model.Event;
import com.denisenko.restdemo.model.File;
import com.denisenko.restdemo.model.User;
import com.denisenko.restdemo.utils.EventAdapter;
import com.denisenko.restdemo.utils.FileAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "eventServlet", value = "/event")
public class EventServlet extends HttpServlet {

    private Gson gson;
    private EventController eventController;
    private FileController fileController;
    private UserController userController;

    @Override
    public void init() throws ServletException {
        eventController = new EventController();
        fileController = new FileController();
        userController = new UserController();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Event.class, new EventAdapter());
        gsonBuilder.registerTypeAdapter(File.class, new FileAdapter());
        gson = gsonBuilder.create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        PrintWriter printWriter = resp.getWriter();

        switch (action) {
            case "showAll" -> printWriter.println(gson.toJson(eventController.getAllEvents()));
            case "getById" -> {
                int id = Integer.parseInt(req.getParameter("id"));
                printWriter.println(gson.toJson(eventController.getByIdEvent(id)));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        String value = req.getParameter("value");
        int fileId = Integer.parseInt(req.getParameter("fileId"));
        int userId = Integer.parseInt(req.getParameter("userId"));


        Event newEvent = new Event(value, fileController.getByIdFile(fileId), userController.getByIdUser(userId));
        eventController.saveEvent(newEvent);
        printWriter.println(gson.toJson(newEvent));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();

        int idUpdateEvent = Integer.parseInt(req.getParameter("id"));
        String value = req.getParameter("value");
        int fileId = Integer.parseInt(req.getParameter("fileId"));
        int userId = Integer.parseInt(req.getParameter("userId"));

        File file = fileController.getByIdFile(fileId);
        User user = userController.getByIdUser(userId);

        Event eventDB = eventController.getByIdEvent(idUpdateEvent);
        eventDB.setValue(value);
        eventDB.setUser(user);

        if (!file.equals(eventDB.getFile()))
            eventDB.setFile(file);

        Event updateEvent = eventController.updateEvent(eventDB);
        printWriter.println(gson.toJson(updateEvent));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));

        printWriter.println(gson.toJson(eventController.deleteEventById(id)));
    }


}
