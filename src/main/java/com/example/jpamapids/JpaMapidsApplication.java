package com.example.jpamapids;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = NaturalRepositoryImpl.class)
@EnableAsync
public class JpaMapidsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaMapidsApplication.class, args);
    }

}
