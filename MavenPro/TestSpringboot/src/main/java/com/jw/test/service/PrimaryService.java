package com.jw.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class PrimaryService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public String getStuName(){
        String sql = "SELECT NAME  FROM STU WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, String.class, 1);
    }
}
