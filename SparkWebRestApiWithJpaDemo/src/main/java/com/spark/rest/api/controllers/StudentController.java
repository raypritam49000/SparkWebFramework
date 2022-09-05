package com.spark.rest.api.controllers;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.spark.rest.api.entity.Student;
import com.spark.rest.api.response.StandardResponse;
import com.spark.rest.api.response.StatusResponse;
import com.spark.rest.api.services.StudentService;
import com.spark.rest.api.services.impl.StudentServiceImpl;

public class StudentController {

	private static final String BASE_URL = "/rest/api";
	private static StudentService studentService = new StudentServiceImpl();
	private static Logger log = LogManager.getLogger(StudentController.class);

	public static void init() {

		post(BASE_URL + "/students", (req, res) -> {
			res.type("application/json");

			try {
				Student student = new Gson().fromJson(req.body(), Student.class);

				if (student != null && !student.getName().equals("") && student.getName() != null
						&& student.getEmail() != null && !student.getEmail().equals("") && student.getCity() != null
						&& !student.getCity().equals("") && student.getRollno() != null
						&& !student.getRollno().equals("")) {

					Student createStudent = studentService.createStudent(student);
					String createdResponse = new Gson().toJson(createStudent);

					return new Gson().toJson(new StandardResponse(StatusResponse.CREATED, 201, "Student Created",
							new Gson().fromJson(createdResponse, JsonElement.class)));
				} else {
					return new Gson().toJson(
							new StandardResponse(StatusResponse.BAD_REQUEST, 401, "All Parameter are required"));
				}
			} catch (Exception e) {
				return new Gson().toJson(
						new StandardResponse(StatusResponse.INTERNAL_SERVER_ERROR, 502, "Internl Server Error"));
			}

		});

		get(BASE_URL + "/getAllStudents", (req, res) -> {
			res.type("application/json");

			try {
				List<Student> students = studentService.getStudents();
				if (students.isEmpty() || students == null) {
					return new Gson().toJson(new StandardResponse(StatusResponse.NOT_FOUND, 404, "Student Not Found"));
				} else {
					return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, 200, "Student List",
							new Gson().toJsonTree(students)));
				}
			} catch (Exception e) {
				return new Gson().toJson(
						new StandardResponse(StatusResponse.INTERNAL_SERVER_ERROR, 502, "Internl Server Error"));
			}

		});

		get(BASE_URL + "/getStudent/:id", (req, res) -> {
			res.type("application/json");
			Long id = Long.parseLong(req.params("id"));
			try {
				Student student = studentService.getStudent(id);
				if (student != null && !student.getName().equals("") && student.getName() != null
						&& student.getEmail() != null && !student.getEmail().equals("") && student.getCity() != null
						&& !student.getCity().equals("") && student.getRollno() != null
						&& !student.getRollno().equals("")) {

					return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, 200, "Student List",
							new Gson().toJsonTree(student)));

				} else {
					return new Gson().toJson(new StandardResponse(StatusResponse.NOT_FOUND, 404, "Student Not Found"));
				}

			} catch (Exception e) {
				return new Gson().toJson(
						new StandardResponse(StatusResponse.INTERNAL_SERVER_ERROR, 502, "Internl Server Error"));
			}

		});

		delete(BASE_URL + "/deleteStudent/:id", (req, res) -> {
			try {
				res.type("application/json");
				Long id = Long.parseLong(req.params("id"));
				studentService.deleteStudent(id);
				return new Gson()
						.toJson(new StandardResponse(StatusResponse.SUCCESS, 203, "Student deleted successfully"));
			} catch (Exception e) {
				return new Gson().toJson(
						new StandardResponse(StatusResponse.INTERNAL_SERVER_ERROR, 502, "Internl Server Error"));
			}
		});

		put(BASE_URL + "/employees/:id", (req, res) -> {
			res.type("application/json");

			try {
				Long id = Long.parseLong(req.params("id"));
				Student student = new Gson().fromJson(req.body(), Student.class);
				if (student != null && !student.getName().equals("") && student.getName() != null
						&& student.getEmail() != null && !student.getEmail().equals("") && student.getCity() != null
						&& !student.getCity().equals("") && student.getRollno() != null
						&& !student.getRollno().equals("")) {

					Student updatedStudent = studentService.updateStudent(id, student);

					if (updatedStudent != null) {
						return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, 204, "Student Updated",
								new Gson().toJsonTree(updatedStudent)));
					} else {
						return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, 401,
								new Gson().toJson("Student not found or error in edit")));
					}

				} else {
					return new Gson().toJson(
							new StandardResponse(StatusResponse.BAD_REQUEST, 401, "All Parameter are required"));
				}
			} catch (Exception e) {
				return new Gson().toJson(
						new StandardResponse(StatusResponse.INTERNAL_SERVER_ERROR, 502, "Internl Server Error"));
			}

		});

	}
}
