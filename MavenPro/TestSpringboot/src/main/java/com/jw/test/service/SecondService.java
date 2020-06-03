package com.jw.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SecondService {
    @Autowired
    @Qualifier("secondJdbcTemplate")
    JdbcTemplate jdbcTemplate;

    public String getStuName(){
        String sql = "SELECT NAME FROM USER WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, String.class, 1);
    }
}
