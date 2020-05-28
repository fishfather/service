package com.jw.spring4.demo1.e_beanscanbyjavaconfig;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class UserService5 {
    public void get() {
        System.out.println("UserService5 get");
    }
}
