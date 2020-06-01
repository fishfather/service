package com.jw.quartz;

import com.jw.quartz.model.Student;
import com.jw.quartz.repo.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyStartupRunner implements CommandLineRunner {

//    @Autowired
//    StuService service;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>MyStartupRunner start<<<<<<<<<<<<<");
    }
}
