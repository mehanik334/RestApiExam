package com.denisenko.restdemo.repository.hibernateImpl;

import com.denisenko.restdemo.model.User;
import com.denisenko.restdemo.repository.UserRepository;
import com.denisenko.restdemo.utils.HibernateSessionFactoryUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class HibernateUserRepository implements UserRepository {

    @Override
    public User getById(Integer integer) {
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        User user = session.get(User.class, integer);
        session.close();
        return user;
    }

    @Override
    public User save(User user) {
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public boolean deleteById(Integer integer) {
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete User where id = :ID");
        query.setParameter("ID", integer);
        boolean res = query.executeUpdate() > 0;
        transaction.commit();
        session.close();
        return res;
    }

    @Override
    public List<User> getAll() {
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        List<User> userList = session.createQuery("select u from User u ", User.class).getResultList();
        session.close();
        return userList;
    }

    @Override
    public User update(User user) {
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
        return getById(user.getId());
    }
}
