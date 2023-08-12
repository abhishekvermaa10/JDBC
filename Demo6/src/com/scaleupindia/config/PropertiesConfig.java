package com.scaleupindia.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.scaleupindia.exception.InternalServiceException;

public class PropertiesConfig {
	private static final Properties properties = new Properties();
	private static final PropertiesConfig propertiesConfig = new PropertiesConfig();

	private PropertiesConfig() {
		try (FileReader fileReader1 = new FileReader("src\\resources\\database.properties");
				FileReader fileReader2 = new FileReader("src\\resources\\messages.properties");) {
			properties.load(fileReader1);
			properties.load(fileReader2);
		} catch (IOException e) {
			throw new InternalServiceException("Failed to load " + e.getMessage());
		}
	}

	public static PropertiesConfig getInstance() {
		return propertiesConfig;
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
