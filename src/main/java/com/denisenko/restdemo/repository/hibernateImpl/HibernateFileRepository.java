package com.denisenko.restdemo.repository.hibernateImpl;

import com.denisenko.restdemo.model.File;
import com.denisenko.restdemo.repository.FileRepository;
import com.denisenko.restdemo.utils.HibernateSessionFactoryUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class HibernateFileRepository implements FileRepository {
    @Override
    public File getById(Integer integer) {
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        File file = session.get(File.class, integer);
        session.close();
        return file;
    }

    @Override
    public File save(File file) {
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(file);
        transaction.commit();
        session.close();
        return getById(file.getId());
    }

    @Override
    public boolean deleteById(Integer integer) {
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete File where id = :ID");
        query.setParameter("ID", integer);
        boolean res = query.executeUpdate() > 0;
        transaction.commit();
        session.close();
        return res;
    }

    @Override
    public List<File> getAll() {
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        List<File> fileList = session.createQuery("select f from File f", File.class).getResultList();
        session.close();
        return fileList;
    }

    @Override
    public File update(File file) {
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(file);
        transaction.commit();
        session.close();
        return getById(file.getId());
    }
}
