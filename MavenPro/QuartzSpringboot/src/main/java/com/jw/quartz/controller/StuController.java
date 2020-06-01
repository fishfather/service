package com.jw.quartz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class StuController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
