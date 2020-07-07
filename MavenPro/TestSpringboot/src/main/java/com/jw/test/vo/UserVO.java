package com.jw.test.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jw.test.util.CustomJsonDateDeserializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class UserVO implements Serializable{
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy/MM/dd")
    @JsonProperty("birth")
    private Date birth;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    @JsonProperty("touch")
    private Date touch;

    @Override
    public String toString() {
        return "UserVO{" +
                "name='" + name + '\'' +
                ", birth=" + birth +
                ", touch=" + touch +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getTouch() {
        return touch;
    }

    public void setTouch(Date touch) {
        this.touch = touch;
    }

    public UserVO(String name, Date birth, Date touch) {
        this.name = name;
        this.birth = birth;
        this.touch = touch;
    }
}
