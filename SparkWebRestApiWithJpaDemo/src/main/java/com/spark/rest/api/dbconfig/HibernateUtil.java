package com.spark.rest.api.dbconfig;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.spark.rest.api.entity.Student;
import com.spark.rest.api.entity.User;
import com.spark.rest.api.file.reader.AppInfo;

public class HibernateUtil {

	public static Session session = null;

	public static Session getSession() {
		try {
			if (session == null) {
				Configuration cfg = new Configuration();
				cfg.setProperty(Environment.DRIVER, AppInfo.DRIVER_CLASS_NAME);
				cfg.setProperty(Environment.URL, AppInfo.URL);
				cfg.setProperty(Environment.USER, AppInfo.USERNAME);
				cfg.setProperty(Environment.PASS, AppInfo.PASSWORD);

				cfg.setProperty(Environment.DIALECT, AppInfo.DIALECT);
				cfg.setProperty(Environment.SHOW_SQL, AppInfo.SHOW_SQL);
				cfg.setProperty(Environment.FORMAT_SQL, AppInfo.FORMAT_SQL);
				cfg.setProperty(Environment.HBM2DDL_AUTO, AppInfo.HBM2DDL_AUTO);

				cfg.addAnnotatedClass(Student.class);
				cfg.addAnnotatedClass(User.class);

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
