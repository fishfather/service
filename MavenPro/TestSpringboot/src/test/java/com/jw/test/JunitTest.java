package com.jw.test;

import com.jw.test.service.PrototypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class JunitTest {

    @Autowired
    private PrototypeService ps;

    @Test
    public void test(){
        System.out.println("start test"+ps);
    }
}
