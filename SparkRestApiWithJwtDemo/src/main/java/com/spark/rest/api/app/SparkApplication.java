package com.spark.rest.api.app;

import static spark.Spark.port;

import org.apache.log4j.BasicConfigurator;

import com.spark.rest.api.controllers.AuthController;
import com.spark.rest.api.controllers.EmployeeController;

public class SparkApplication {

	public static void main(String[] args) {
		port(9999);
		BasicConfigurator.configure();
		AuthController.init();
		EmployeeController.init();

	}
}