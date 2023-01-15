package com.spark.rest.api.file.reader;

import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {

	public static String getValue(String key) {
		Properties properties = new Properties();
		try {
			InputStream in = PropertyFileReader.class.getClassLoader().getResourceAsStream("application.properties");
			properties.load(in);
			return properties.getProperty(key);
		} catch (Exception e) {
             e.printStackTrace();
		}
		return properties.getProperty(key);
	}
	
}
