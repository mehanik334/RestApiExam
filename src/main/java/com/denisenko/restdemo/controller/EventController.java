package com.denisenko.restdemo.controller;

import com.denisenko.restdemo.model.Event;
import com.denisenko.restdemo.service.EventService;

import java.util.List;

public class EventController {

    private EventService eventService;

    public EventController() {
        eventService = new EventService();
    }

    public Event getByIdEvent(Integer id) {
        return eventService.getById(id);
    }

    public Event saveEvent(Event event) {
        return eventService.save(event);
    }

    public boolean deleteEventById(Integer id) {
        return eventService.delete(id);
    }

    public List<Event> getAllEvents() {
        return eventService.getAll();
    }

    public Event updateEvent(Event event) {
        return eventService.update(event);
    }
}
