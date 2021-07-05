package com.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StreamTest2 {

    @Test
    public void test1(){
        List<Map> list = new ArrayList<>();
        list.add(create("Sam", "11", "Sam Sum"));
        list.add(create("Look", "12", "Look Sum"));
        list.add(create("House", "13", "House Sum"));

        Map o = list.stream().filter(e -> e.get("name").equals("Look1")).findFirst().orElse(null);
        System.out.println(o);
    }

    private static Map create(String name, String age, String nickname){
        Map<Object, Object> m = new HashMap<>();
        m.put("name" , name);
        m.put("age" , age);
        m.put("nickname" , nickname);
        return m;
    }
}
