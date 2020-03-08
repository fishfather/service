package com.jw.spring4.demo1.b_scanbean;

import org.springframework.stereotype.Component;

@Component
public class UserService3Impl implements UserService3 {
    public void get() {
        System.out.println("UserService3Impl get");
    }
}
