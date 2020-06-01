package com.jw.quartz.model;

public class User {
    private Integer age;
    private String name;
    private String male;

    public User(Integer age, String name, String male) {
        this.age = age;
        this.name = name;
        this.male = male;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", male='" + male + '\'' +
                '}';
    }
}
