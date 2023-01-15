package com.spark.rest.api.controllers;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.spark.rest.api.entity.Employee;
import com.spark.rest.api.response.StandardResponse;
import com.spark.rest.api.response.StatusResponse;
import com.spark.rest.api.services.EmployeeService;
import com.spark.rest.api.services.EmployeeServiceImpl;

public class EmployeeController {

	public static String BASE_URL="/rest/api";
	public static EmployeeService employeeService = new EmployeeServiceImpl();

	public static void init() {


		post(BASE_URL+"/employees", (request, response) -> {
			response.type("application/json");

			Employee employee = new Gson().fromJson(request.body(), Employee.class);
			Employee createEmployee = employeeService.addEmployee(employee);
			String createdResponse = new Gson().toJson(createEmployee);
			return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "Employee Created",
					new Gson().fromJson(createdResponse, JsonElement.class)));
		});

		get(BASE_URL+"/employees", (request, response) -> {
			response.type("application/json");

			List<Employee> employees = employeeService.getEmployees();

			return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "Get All Employess",
					new Gson().toJsonTree(employees)));
		});

		get(BASE_URL+"/employees/:id", (request, response) -> {
			response.type("application/json");

			Long id = Long.parseLong(request.params("id"));
			Employee employee = employeeService.getEmployee(id);

			return new Gson().toJson(
					new StandardResponse(StatusResponse.SUCCESS, "Get All Employess", new Gson().toJsonTree(employee)));
		});

		delete(BASE_URL+"/employees/:id", (request, response) -> {
			response.type("application/json");

			employeeService.deleteEmployee(Long.parseLong(request.params("id")));
			return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "Employee deleted successfully"));
		});

		put(BASE_URL+"/employees/:id", (request, response) -> {
			response.type("application/json");

			Long id = Long.parseLong(request.params("id"));
			Employee employee = new Gson().fromJson(request.body(), Employee.class);
			Employee updatedEmployee = employeeService.updateEmployee(id, employee);

			if (updatedEmployee != null) {
				return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "Employee Updated",
						new Gson().toJsonTree(updatedEmployee)));
			} else {
				return new Gson().toJson(new StandardResponse(StatusResponse.ERROR,
						new Gson().toJson("Employee not found or error in edit")));
			}
		});

	}
}
