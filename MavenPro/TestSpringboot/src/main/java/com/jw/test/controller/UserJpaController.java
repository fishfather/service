package com.jw.test.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jw.test.entity.User;
import com.jw.test.repo.UserRepository;
import com.jw.test.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaController {
    @Autowired
    UserRepository userRepository;

    //VO Test with json format data
    @RequestMapping(value = "uservo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public UserVO userVO(@RequestBody UserVO userVO){
        System.out.println("UserVO is:"+userVO);
        return userVO;
    }

    //VO Test with form data
    @RequestMapping(value = "uservo2", method = RequestMethod.POST)
    public UserVO userVO2(UserVO userVO){
        System.out.println("UserVO is:"+userVO);
        return userVO;
    }

    @RequestMapping(value = "uservo3", method = RequestMethod.POST)
    public String userVO3(@RequestBody String jsonpObject){     //Actually RequestBody accepts a json format string
        System.out.println("JsonObj is:"+jsonpObject.toString());
        return "userVO2";
    }

    //
//    {
//        "name":"jiangwei",
//            "birth":"2020-06-22",
//            "touch":"2020-06-22 08:10:12"
//    }
    @RequestMapping(value = "uservo4", method = RequestMethod.POST)
    public String userVO4(@RequestBody HashMap<String, Object> jsonpObject){     //Actually RequestBody accepts a json format string which can be convert to a map
        System.out.println("JsonObj is:"+jsonpObject.toString());
        return "userVO2";
    }

    //--------------------------------

    @RequestMapping(value = "/user/{name}")
    public String addUser(@PathVariable("name") String name){
        System.out.println("Create user:"+name);
        userRepository.save(new User(name, new Date()));
        return "user created:"+name;
    }

    @RequestMapping("/user2/{id}")
    public User findUser(@PathVariable("id") String id){
        Optional<User> op = userRepository.findById(Integer.valueOf(id));
        if(op.isPresent())
            return op.get();
        return null;
    }

    @RequestMapping("/user3")
    public List<User> findAllUser(){
        List<User> all = userRepository.findAll();
        return all;
    }

    @RequestMapping("/deleteuser")
    public String deleteUser(Integer id){
        userRepository.deleteById(id);
        return "true";
    }

    @RequestMapping("/user5")
    public User findUserByName(String name){
        User jiangwei = userRepository.findByName(name);
        return jiangwei;
    }

    @RequestMapping("/user6")
    public List<User> findAllUserNativeSQL(String name){
        return userRepository.findAllUsers();
    }

    @RequestMapping("/user7")
    public List<User> findPageUser(){
        Pageable pageable = PageRequest.of(1, 3); //查找第2页，每页3条数据 （从0开始）
        Page<User> pageUsers = userRepository.findPageUsers(pageable);
        return pageUsers.getContent();
    }

    @RequestMapping("/user/update")
    public User updateUserByName(Integer id) throws ParseException {
        Optional<User> userOp = userRepository.findById(id);
        User user = userOp.get();
        user.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1985-12-19"));
        return userRepository.save(user);
    }

    @RequestMapping("/user/update2")
    public String updateUserWithName(Integer id) throws ParseException {
        int i = userRepository.modifyById("JIANGWEI", id);
        return ""+i;
    }
}
