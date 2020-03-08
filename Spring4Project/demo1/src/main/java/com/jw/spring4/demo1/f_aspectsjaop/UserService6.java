package com.jw.spring4.demo1.f_aspectsjaop;

public class UserService6 {
    public String get(){
        System.out.println("UserService6 get()");
        return "testUser";
    }
    public void update(){
        System.out.println("UserService6 update()");
    }

    public void throwMethod(){
        System.out.println("UserService6 throwMethod");
        throw new RuntimeException("error happens for throwMethod");
    }
}
