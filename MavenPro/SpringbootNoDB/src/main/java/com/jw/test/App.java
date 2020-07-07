package com.jw.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class App {
    public static void main(String[] args) {
        System.out.println("App starting....");
        SpringApplication.run(App.class, args);
    }
}