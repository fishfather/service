package com.jw.test.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    @Autowired
    UserInternalEntitlement userInternalEntitlement;

    @Autowired
    UserExternalEntitlement userExternalEntitlement;

    public UserEntitlement get(String type){
        TYPE uType = null;
        try {
            uType = TYPE.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("User type is not valid.");
        }

        if(uType == TYPE.I){
            return userInternalEntitlement;
        }else{
            return userExternalEntitlement;
        }
    }

    enum TYPE{
        I, E
    }

//    public static UserEntitlement create(String type){
//        if("I".equals(type)){
//            return new UserInternalEntitlement();
//        }else{
//            return new UserExternalEntitlement();
//        }
//    }
}
