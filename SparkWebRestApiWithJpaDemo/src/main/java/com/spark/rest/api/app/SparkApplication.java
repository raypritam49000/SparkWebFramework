package com.spark.rest.api.app;

import static spark.Spark.ipAddress;
import static spark.Spark.port;

import org.apache.log4j.BasicConfigurator;

import com.spark.rest.api.controllers.AuthController;
import com.spark.rest.api.controllers.StudentController;
import com.spark.rest.api.file.reader.AppInfo;

public class SparkApplication {
	public static void main(String[] args) {
		ipAddress(AppInfo.SERVER_ADDRESS);
		port(AppInfo.SERVER_PORT);
		BasicConfigurator.configure();
		AuthController.init();
		StudentController.init();
	}
}
