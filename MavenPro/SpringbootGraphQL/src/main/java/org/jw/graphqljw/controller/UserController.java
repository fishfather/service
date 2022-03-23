package org.jw.graphqljw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String hello(){
        System.out.println("Enter user.");
        return "success";
    }
}

