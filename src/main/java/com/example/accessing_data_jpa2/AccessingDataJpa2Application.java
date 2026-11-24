package com.example.accessing_data_jpa2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class AccessingDataJpa2Application {
	private static final Logger logger = LoggerFactory.getLogger(AccessingDataJpa2Application.class);


	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpa2Application.class, args);
	}



}
