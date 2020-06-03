package com.jw.test;

import com.jw.test.service.PrimaryService;
import com.jw.test.service.SecondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Starter implements CommandLineRunner {
    @Autowired
    PrimaryService primaryService;

    @Autowired
    SecondService secondService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("start");
        System.out.println("First DB data is:"+primaryService.getStuName());
        System.out.println("Second DB data is:"+secondService.getStuName());

        Nothing n1 = new Nothing();
        Nothing n2 = new Nothing();
        System.out.println(n1.get());
        System.out.println(n2.get());

    }
}
