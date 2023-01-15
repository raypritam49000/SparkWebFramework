package com.spark.rest.api.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.spark.rest.api.entity.User;
import com.spark.rest.api.util.HibernateUtils;

public class UserRepository implements Repository<Long, User> {

    private SessionFactory sessionFactory;

    public UserRepository() {
        this.sessionFactory = HibernateUtils.getSessionFactory();
    }

    @Override
    public User save(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.flush();
            session.close();
            return user;
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.flush();
            session.close();
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<User> query = session.createQuery("from User", User.class);
            List<User> users = query.getResultList();
            session.close();
            return users;
        }
    }

    @Override
    public User findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            System.out.println(user);
            session.close();
            return user;
        }
    }

}