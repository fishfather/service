package com.jw.test.user;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class UserEntitlement {
    @Autowired
    protected UserService userService;

    public void getUser(){
        System.out.println("UserEntitlement getUser / "+userService);
    }

    public abstract void getClient();
}
