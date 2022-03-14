package com.denisenko.restdemo.servlets;

import com.denisenko.restdemo.controller.EventController;
import com.denisenko.restdemo.controller.FileController;
import com.denisenko.restdemo.controller.UserController;
import com.denisenko.restdemo.model.Event;
import com.denisenko.restdemo.model.File;
import com.denisenko.restdemo.utils.EventAdapter;
import com.denisenko.restdemo.utils.FileAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "fileServlet", value = "/file")
public class FileServlet extends HttpServlet {
    private FileController fileController;
    private EventController eventController;
    private UserController userController;
    private Gson gson;

    public void init() {
        fileController = new FileController();
        eventController = new EventController();
        userController = new UserController();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Event.class, new EventAdapter());
        gson = gsonBuilder.registerTypeAdapter(File.class, new FileAdapter()).create();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        String location = req.getParameter("location");
        String valueEvent = req.getParameter("valueEvent");
        String idUser = req.getParameter("idUser");

        Event event = new Event(valueEvent, userController.getByIdUser(Integer.parseInt(idUser)));
        Event savedEvent = eventController.saveEvent(event);
        File file = new File(location, savedEvent);
        File savedFile = fileController.saveFile(file);

        savedEvent.setFile(savedFile);
        savedFile.setEvent(savedEvent);
        fileController.updateFile(savedFile);
        eventController.updateEvent(savedEvent);

        printWriter.println(gson.toJson(savedFile));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        PrintWriter printWriter = resp.getWriter();

        switch (action) {

            case "showAll" -> printWriter.println(gson.toJson(fileController.getAllFiles()));

            case "getById" -> {
                int id = Integer.parseInt(req.getParameter("id"));
                File file = fileController.getByIdFile(id);
                printWriter.println(gson.toJson(file));
            }
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));
        printWriter.println(gson.toJson(fileController.deleteFileById(id)));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();

        int idUpdateFile = Integer.parseInt(req.getParameter("id"));
        String locationUpdate = req.getParameter("location");
        File updateFile = new File(idUpdateFile, locationUpdate);
        printWriter.println(gson.toJson(fileController.updateFile(updateFile)));
    }
}
