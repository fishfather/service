package com.jw.test.user;

import org.springframework.stereotype.Component;

@Component
public class UserInternalEntitlement extends UserEntitlement {
    @Override
    public void getClient() {
        System.out.println("UserInternalEntitlement getClient /"+userService);
    }
}
