package com.jw.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages={"com.jw.test.entity"})
@EnableJpaRepositories(basePackages={"com.jw.test.repo"})
public class App {
    public static void main(String[] args) {
        System.out.println("App starting....");
        SpringApplication.run(App.class, args);
    }
}