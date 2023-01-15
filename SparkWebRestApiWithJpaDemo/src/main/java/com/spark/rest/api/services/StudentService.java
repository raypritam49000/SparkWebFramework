package com.spark.rest.api.services;

import java.util.List;

import com.spark.rest.api.entity.Student;

public interface StudentService {
	public abstract Student createStudent(Student student);
	public abstract List<Student> getStudents();
	public abstract Student getStudent(Long id);
	public abstract void deleteStudent(Long id);
	public abstract Student updateStudent(Long id,Student updateStudent);
}
