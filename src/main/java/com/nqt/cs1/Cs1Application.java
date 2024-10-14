package com.nqt.cs1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {
		SecurityAutoConfiguration.class
})

//@SpringBootApplication
public class Cs1Application {
	public static void main(String[] args) {
		SpringApplication.run(Cs1Application.class, args);
	}
}
