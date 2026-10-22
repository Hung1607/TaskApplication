package com.example.taskadministration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TaskAdministrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskAdministrationApplication.class, args);
	}

}
