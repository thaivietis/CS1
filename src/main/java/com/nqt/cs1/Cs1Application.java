package com.nqt.cs1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication(exclude = {
//		SecurityAutoConfiguration.class
//})

@SpringBootApplication
@EnableScheduling
public class Cs1Application {
	public static void main(String[] args) {
		SpringApplication.run(Cs1Application.class, args);
	}
}
