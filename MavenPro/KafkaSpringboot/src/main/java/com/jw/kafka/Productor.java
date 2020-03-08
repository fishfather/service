package com.jw.kafka;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Productor {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(String name){
        User u=new User();
        u.setName(name);
        u.setAge(11);
        System.out.println("productor create ");
        kafkaTemplate.send("test-topic1", JSON.toJSONString(u));
    }
}
