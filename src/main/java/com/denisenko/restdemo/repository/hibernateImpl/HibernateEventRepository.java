package com.denisenko.restdemo.repository.hibernateImpl;

import com.denisenko.restdemo.model.Event;
import com.denisenko.restdemo.repository.EventRepository;
import com.denisenko.restdemo.utils.HibernateSessionFactoryUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class HibernateEventRepository implements EventRepository {
    @Override
    public Event getById(Integer integer) {
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        Event event = session.get(Event.class, integer);
        session.close();
        return event;
    }

    @Override
    public Event save(Event event) {
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(event);
        transaction.commit();
        session.close();
        return getById(event.getId());
    }

    @Override
    public boolean deleteById(Integer integer) {
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete Event where id = :ID");
        query.setParameter("ID", integer);
        boolean res = query.executeUpdate() > 0;
        transaction.commit();
        session.close();
        return res;
    }

    @Override
    public List<Event> getAll() {
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        List<Event> eventList = session.createQuery("select e from Event e", Event.class).getResultList();
        session.close();
        return eventList;
    }

    @Override
    public Event update(Event event) {
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(event);
        transaction.commit();
        session.close();
        return getById(event.getId());
    }
}
