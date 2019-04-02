package com.excilys.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerConfigurator {

	private static void addConfiguration() {
		PropertyConfigurator.configure("computer-database/ressources/log4j.properties");
	}

	/**
	 * 
	 * @param classname
	 * @return
	 */
	public static Logger configureLogger(Class<?> classname) {
		System.out.println(System.getProperty("java.class.path"));
		Logger logger;
		logger = Logger.getLogger(classname);
		addConfiguration();
		return logger;
	}

}
