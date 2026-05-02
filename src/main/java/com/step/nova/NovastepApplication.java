package com.step.nova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NovastepApplication {

	public static void main(String[] args) {
            System.out.println("TNS_ADMIN=" + System.getenv("TNS_ADMIN"));

		SpringApplication.run(NovastepApplication.class, args);
	}

}
