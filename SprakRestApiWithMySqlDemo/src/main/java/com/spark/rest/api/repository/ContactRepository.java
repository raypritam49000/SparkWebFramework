package com.spark.rest.api.repository;


import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.spark.rest.api.entity.Contact;
import com.spark.rest.api.util.HibernateUtils;

public class ContactRepository implements Repository<Long, Contact> {

    private SessionFactory sessionFactory;

    public ContactRepository() {
        this.sessionFactory = HibernateUtils.getSessionFactory();
    }

    @Override
    public Contact save(Contact contact) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(contact);
            session.flush();
            session.close();
            return contact;
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Contact contact = session.get(Contact.class, id);
            session.delete(contact);
            session.flush();
            session.close();
        }
    }

    @Override
    public List<Contact> findAll() {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<Contact> query = session.createQuery("from Contact", Contact.class);
            List<Contact> contacts = query.getResultList();
            session.close();
            return contacts;
        }
    }

    @Override
    public Contact findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Contact contact = session.load(Contact.class, id);
            session.close();
            return contact;
        }
    }

}