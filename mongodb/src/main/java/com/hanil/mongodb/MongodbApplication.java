package com.hanil.mongodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodbApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository){
		return args -> {

			Student student = new Student(
					"Hanil","Trivedi","ht@ghgh.com",Gender.MALE ,new Address("India","126152",
					"Bangalore"), List.of("Maths","science"), BigDecimal.TEN, LocalDateTime.now()
			);

			repository.insert(student);

		};
	}
}
