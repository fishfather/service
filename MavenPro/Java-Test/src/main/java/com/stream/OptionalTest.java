package com.stream;

import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        String str = "null";
        Optional<String> s1 = Optional.ofNullable(str).map(s -> s + "_append");
        if(s1.isPresent()){
            System.out.println("str is not null");
        }else{
            System.out.println("str is null");
        }
        //if str is null and not empty, return str, else return default
        String val2 = Optional.ofNullable(str).map(s -> s.isEmpty()?null:s).orElse("default");
        System.out.println(val2);
    }
}
