package com.jw.quartz.repo;

import com.jw.quartz.model.Job;
import com.jw.quartz.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class StuService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Student> getList(){
        String sql = "SELECT ID,NAME,AGE  FROM STU";
        List<Student> query = (List<Student>) jdbcTemplate.query(sql, new RowMapper<Student>() {

            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student stu = new Student();
                stu.setId(rs.getInt("ID"));
                stu.setAge(rs.getInt("AGE"));
                stu.setName(rs.getString("NAME"));
                return stu;
            }

        });
        return query;
    }

    public List<Job> getJobs(){
        String sql = "SELECT NAME, INFO  FROM JOBS WHERE ACTIVE = 'Y'";
        List<Job> query = (List<Job>) jdbcTemplate.query(sql, new RowMapper<Job>() {

            @Override
            public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
                Job stu = new Job();
                stu.setInfo(rs.getString("INFO"));
                stu.setName(rs.getString("NAME"));
                return stu;
            }

        });
        return query;
    }
}
