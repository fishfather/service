package com.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestJson {
    public static void main(String[] args) throws Exception {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("username", "admin");
        map.put("pwd", "22");
        map.put("age", 20);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(map);
        System.out.println(jsonStr);

        JsonNode jsonNode = objectMapper.readTree(jsonStr);
        System.out.println( jsonNode.get("age").asText() );

        JsonNode jsonNode1 = objectMapper.readTree(jsonStr);
        System.out.println(jsonNode1.toString());

        ObjectNode newNode = objectMapper.createObjectNode();
        newNode.setAll((ObjectNode)jsonNode1);
        newNode.put("hometown", "yiyi");

        System.out.println(newNode.toString());

    }
}
