package com.jw.quartz.config;

import com.jw.quartz.job.QJob;
import org.quartz.*;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
public class QuartzCustomConfig implements SchedulerFactoryBeanCustomizer {

    @Override
    public void customize(SchedulerFactoryBean schedulerFactoryBean) {
//        schedulerFactoryBean.setSchedulerName("FIRST_SCHEDULE_JW");

        schedulerFactoryBean.setOverwriteExistingJobs(true);

        JobDetail jobDetail1 = JobBuilder.newJob(QJob.class).withIdentity("testQuartzJob1", "group1").storeDurably().build();
        JobDetail jobDetail2 = JobBuilder.newJob(QJob.class).withIdentity("testQuartzJob2", "group1").storeDurably().build();
        schedulerFactoryBean.setJobDetails(jobDetail1, jobDetail2);

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever().withMisfireHandlingInstructionNextWithRemainingCount();
        Trigger trigger1 = TriggerBuilder.newTrigger().forJob(jobDetail1).withIdentity("testQuartzTriggerA", "group1").withSchedule(scheduleBuilder).build();

        SimpleScheduleBuilder scheduleBuilder2 = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever().withMisfireHandlingInstructionNextWithRemainingCount();
        Trigger trigger2 = TriggerBuilder.newTrigger().forJob(jobDetail2).withIdentity("testQuartzTriggerB", "group1").withSchedule(scheduleBuilder2).build();

        schedulerFactoryBean.setTriggers(trigger1, trigger2);
    }
}
