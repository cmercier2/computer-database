package com.excilys.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerConfigurator {
	
	private static void addConfiguration() {
		PropertyConfigurator.configure("./ressources/log4j.properties");
	}

	public static Logger configureLogger(Class<?> classname) {
		Logger logger;
		logger = Logger.getLogger(classname);
		addConfiguration();
		return logger;
	}
	
}
