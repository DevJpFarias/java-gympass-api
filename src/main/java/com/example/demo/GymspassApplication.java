package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(FlywayConfig.class)
public class GymspassApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymspassApplication.class, args);
	}

}
