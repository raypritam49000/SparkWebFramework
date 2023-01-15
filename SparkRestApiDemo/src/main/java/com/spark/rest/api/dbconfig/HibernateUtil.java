package com.spark.rest.api.dbconfig;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.spark.rest.api.entity.Employee;

public class HibernateUtil {

	public static Session session = null;

	public static Session getSession() {
		try {
			if (session == null) {
				Configuration cfg = new Configuration();
				cfg.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				cfg.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/test");
				cfg.setProperty(Environment.USER, "root");
				cfg.setProperty(Environment.PASS, "1998");

				cfg.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL57Dialect");
				cfg.setProperty(Environment.SHOW_SQL, "true");
				cfg.setProperty(Environment.FORMAT_SQL, "true");
				cfg.setProperty(Environment.HBM2DDL_AUTO, "update");

				cfg.addAnnotatedClass(Employee.class);

				SessionFactory sessionFactory = cfg.buildSessionFactory();

				session = sessionFactory.openSession();
				return session;
			} else {
				return session;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}
}
