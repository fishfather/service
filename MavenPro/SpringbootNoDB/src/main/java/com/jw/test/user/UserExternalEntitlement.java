package com.jw.test.user;

import org.springframework.stereotype.Component;

@Component
public class UserExternalEntitlement extends UserEntitlement {
    @Override
    public void getClient() {
        System.out.println("UserExternalEntitlement getClient / "+userService);
    }
}
