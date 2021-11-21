package com.hanil.mongodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class MongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate) {
        return args -> {

            String email = "st@ghgh.com";
            Student student = new Student(
                    "Saurabh", "Trivedi", email, Gender.MALE, new Address("India", "126152",
                    "Pune"), List.of("Sanskrit", "science", "Arts"), BigDecimal.TEN, LocalDateTime.now()
            );

            Query query = new Query();
            query.addCriteria(Criteria.where("email").is(email));
            List<Student> students = mongoTemplate.find(query, Student.class);
            if (students.size()>1){
                throw new IllegalStateException("Found many students with email - "+email);
            }
            if(students.isEmpty()){
                System.out.println("inserting student "+student);
                repository.insert(student);
            }
            else {
                System.out.println("already with same email id exists - "+email);
            }
        };
    }
}
