package com.jw.quartz.model;

public class Job {
    private String name;
    private String info;
    private String active;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Job() {
    }

    @Override
    public String toString() {
        return "Job{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }

    public Job(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }
}
