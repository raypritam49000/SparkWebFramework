package com.spark.rest.api.services.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.spark.rest.api.dbconfig.HibernateUtil;
import com.spark.rest.api.entity.Student;
import com.spark.rest.api.services.StudentService;

public class StudentServiceImpl implements StudentService {

	private Session session = HibernateUtil.getSession();

	@Override
	public Student createStudent(Student student) {
		Transaction trx = session.beginTransaction();
		Serializable sid = session.save(student);
		trx.commit();
		return session.get(Student.class, sid);
	}

	@Override
	public List<Student> getStudents() {
		Query query = session.createQuery("From Student");
		List<Student> students = query.getResultList();
		return students;
	}

	@Override
	public Student getStudent(Long id) {
		return session.get(Student.class, id);
	}

	@Override
	public void deleteStudent(Long id) {
		Transaction trx = session.beginTransaction();
		session.remove(session.get(Student.class, id));
		trx.commit();
	}

	@Override
	public Student updateStudent(Long id, Student updateStudent) {
		Serializable sid = null;
		Transaction trx = session.beginTransaction();
		Student student = session.get(Student.class, id);
		if (student != null) {
			student.setEmail(updateStudent.getEmail());
			student.setName(updateStudent.getName());
			student.setRollno(updateStudent.getRollno());
			student.setCity(updateStudent.getCity());
			sid = this.session.save(student);
		}
		trx.commit();
		return session.get(Student.class, sid);
	}
}
