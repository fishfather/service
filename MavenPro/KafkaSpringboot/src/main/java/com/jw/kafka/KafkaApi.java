package com.jw.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaApi {
    @Autowired
    private Productor product;

    @RequestMapping("/send")
    public void send(String name){
        System.out.println("start to send." + name);
        product.send(name);
    }
}
