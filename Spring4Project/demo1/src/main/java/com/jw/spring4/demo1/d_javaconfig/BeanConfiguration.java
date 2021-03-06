package com.jw.spring4.demo1.d_javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = "com.jw.spring4.demo1.e_beanscanbyjavaconfig")
public class BeanConfiguration {

    @Scope("prototype")
    @Bean
    public UserService4 getUserService4(){
        System.out.println("Start to init UserService4 by BeanConfiguration");
        return new UserService4();
    }
}
