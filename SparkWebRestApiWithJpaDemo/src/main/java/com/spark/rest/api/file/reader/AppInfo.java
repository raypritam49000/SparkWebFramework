package com.spark.rest.api.file.reader;

public class AppInfo {
	public static final Integer SERVER_PORT = Integer.parseInt(PropertyFileReader.getValue("server.port"));
	public static final String SERVER_ADDRESS = PropertyFileReader.getValue("server.address");
	public static final String DRIVER_CLASS_NAME = PropertyFileReader.getValue("spark.datasource.driver-class-name");
	public static final String URL = PropertyFileReader.getValue("spark.datasource.url");
	public static final String USERNAME = PropertyFileReader.getValue("spark.datasource.username");
	public static final String PASSWORD = PropertyFileReader.getValue("spark.datasource.password");
	public static final String DIALECT = PropertyFileReader.getValue("spark.jpa.properties.hibernate.dialect");
	public static final String HBM2DDL_AUTO = PropertyFileReader.getValue("spark.jpa.properties.hibernate.hbm2ddl.auto");
	public static final String SHOW_SQL = PropertyFileReader.getValue("spark.jpa.properties.hibernate.show_sql");
	public static final String FORMAT_SQL = PropertyFileReader.getValue("spark.jpa.properties.hibernate.format_sql");
}
