package com.databasecontroller.DatabaseController;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.databasecontroller.DatabaseController.Controller.APIController;
import com.databasecontroller.DatabaseController.Model.ContactDatabase;

@SpringBootApplication
public class DatabaseControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseControllerApplication.class, args);
	}

}
