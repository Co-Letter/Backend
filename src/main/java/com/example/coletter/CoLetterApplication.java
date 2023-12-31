package com.example.coletter;

import com.example.coletter.model.entity.Letter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CoLetterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoLetterApplication.class, args);

    }

}
