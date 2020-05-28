package com.jw.swagger.controller;

import com.jw.swagger.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="User Manager")
@RestController
@RequestMapping("user")
public class UserController {

    @ApiOperation("用户详情")
    @RequestMapping(value = "getuser", method = RequestMethod.GET)
    public User hello(){
        System.out.println("Enter user.");
        User user = new User(18, "Jw", "M");
        return user;
    }
}
