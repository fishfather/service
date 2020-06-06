package com.jw.quartz.config;

import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzCustomConfig implements SchedulerFactoryBeanCustomizer {

    @Override
    public void customize(SchedulerFactoryBean schedulerFactoryBean) {
        System.out.println("------------------------- **************** start");

        schedulerFactoryBean.setApplicationContextSchedulerContextKey("appContext");

        schedulerFactoryBean.setAutoStartup(false);
    }
}
