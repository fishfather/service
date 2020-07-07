package com.jw.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MvcController {

    @RequestMapping("test/test")
    @ResponseBody
    public String test(){
        System.out.println("test enter");
        return "test";
    }

    @RequestMapping("test/login")
    @ResponseBody
    public String login(){
        System.out.println("login enter");
        return "login";
    }

    @RequestMapping("test/test_1")
    public String forwardToTest(){
        System.out.println("forwardToTest enter");
        return "/test/test";
    }

    @RequestMapping("test/test2")
    public String navToTest(){
        System.out.println("navToTest enter");
        return "test.html";
    }

    @RequestMapping("test/test3")
    public String navToTest2(){
        System.out.println("navToTest2 enter");
        return "/test.html";
    }

    @RequestMapping("test/test4")
    public String navToTest4(){
        System.out.println("navToTest4 enter");
        return "redirect:/test.html";
    }
}
