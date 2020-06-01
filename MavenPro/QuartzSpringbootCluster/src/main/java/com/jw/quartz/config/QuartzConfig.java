package com.jw.quartz.config;


import com.jw.quartz.job.QJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;

//@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail teatQuartzDetail() {
        return JobBuilder.newJob(QJob.class).withIdentity("testQuartzJob").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever();
        return TriggerBuilder.newTrigger().forJob(teatQuartzDetail()).withIdentity("testQuartzTrigger").withSchedule(scheduleBuilder) .build();

    }
}