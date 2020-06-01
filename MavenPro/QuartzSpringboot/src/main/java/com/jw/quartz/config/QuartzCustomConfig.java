package com.jw.quartz.config;

import com.jw.quartz.job.QJob;
import org.quartz.*;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class QuartzCustomConfig implements SchedulerFactoryBeanCustomizer {

    @Override
    public void customize(SchedulerFactoryBean schedulerFactoryBean) {
//        System.out.println("out put :"+schedulerFactoryBean);
//        System.out.println("---------------------------"+schedulerFactoryBean.isAutoStartup());
        schedulerFactoryBean.setSchedulerName("FIRST_SCHEDULE_JW");

        JobDetail jobDetail1 = JobBuilder.newJob(QJob.class).withIdentity("testQuartzJob1").storeDurably().build();
        JobDetail jobDetail2 = JobBuilder.newJob(QJob.class).withIdentity("testQuartzJob2").storeDurably().build();
        schedulerFactoryBean.setJobDetails(jobDetail1,jobDetail2);

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever();
        Trigger trigger1  = TriggerBuilder.newTrigger().forJob(jobDetail1).withIdentity("testQuartzTriggerA").withSchedule(scheduleBuilder) .build();

        SimpleScheduleBuilder scheduleBuilder2 = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(8).repeatForever();
        Trigger trigger2  = TriggerBuilder.newTrigger().forJob(jobDetail2).withIdentity("testQuartzTriggerB").withSchedule(scheduleBuilder2) .build();

        schedulerFactoryBean.setTriggers(trigger1,trigger2);
    }
}
