package com.learn.base;

import java.io.FileNotFoundException;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

public class Junit4ClassRunner extends SpringJUnit4ClassRunner {

	public Junit4ClassRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
	}

	static {
		try {
			Log4jConfigurer.initLogging("classpath:properties/log/log4j.properties");
		} catch (FileNotFoundException ex) {
			System.err.println("Cannot Initialize log4j");
		}
	}

}
