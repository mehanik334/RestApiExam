package com.denisenko.restdemo.service;

import com.denisenko.restdemo.model.Event;
import com.denisenko.restdemo.repository.EventRepository;
import com.denisenko.restdemo.repository.hibernateImpl.HibernateEventRepository;

import java.util.List;

public class EventService {

    private EventRepository eventRepository = new HibernateEventRepository();

    public EventRepository getEventRepository() {
        return eventRepository;
    }

    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event getById(Integer id) {
        return eventRepository.getById(id);
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public boolean delete(Integer id) {
        return eventRepository.deleteById(id);
    }

    public List<Event> getAll() {
        return eventRepository.getAll();
    }

    public Event update(Event event) {
        return eventRepository.update(event);
    }
}
