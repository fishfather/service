package com.jw.spring4.demo1.b_scanbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService3 {

    @Autowired
    private UserService3 us;

    public void get(){
        us.get();
    }
}
