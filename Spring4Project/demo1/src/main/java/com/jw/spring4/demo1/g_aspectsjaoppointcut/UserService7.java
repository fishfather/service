package com.jw.spring4.demo1.g_aspectsjaoppointcut;

public class UserService7 {
    public String get(){
        System.out.println("UserService7 get()");
        return "testUser";
    }
    public void update(){
        System.out.println("UserService7 update()");
    }

    public void throwMethod(){
        System.out.println("UserService7 throwMethod");
        throw new RuntimeException("error happens for throwMethod");
    }
}
