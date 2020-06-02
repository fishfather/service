package com.jw.quartz.manager;

import com.jw.quartz.job.QJob;
import com.jw.quartz.model.Job;
import com.jw.quartz.repo.StuService;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuartzManager {
    private static final String JOB_GROUP_NAME = "GROUP_JW";

    @Autowired
    StuService service;

    @Autowired
    Scheduler scheduler;

    public void updateAllQuartzJobs() {
        try {
            List<Job> jobs = service.getJobs();
            List<String> availableJobs = jobs.stream().map(e -> e.getName()).collect(Collectors.toList());
            Set<JobKey> jobKeys = null;

            jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(JOB_GROUP_NAME));

            List<String> removeJobs = new ArrayList<>();
            jobKeys.stream().forEach(e -> {
                if (availableJobs.contains(e.getName())) {
                    availableJobs.remove(e.getName());
                } else {
                    removeJobs.add(e.getName());
                }
            });

            System.out.println("available jobs:" + availableJobs);
            System.out.println("delete jobs:" + removeJobs);
            removeJobs.forEach(e -> deleteJob(e));
            availableJobs.forEach(e -> addJob(e, "33,39 * * * * ? ", e + "Test Data"));

            System.out.println("Update job successful.");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void addJob(String jobName, String time, String data) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(QJob.class).withIdentity(jobName, JOB_GROUP_NAME)
                    .usingJobData("data", data)
                    .build();    //用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
            CronTrigger trigger = TriggerBuilder
                    .newTrigger()                                                                        //创建一个新的TriggerBuilder来规范一个触发器
                    .withIdentity(jobName, JOB_GROUP_NAME)                                            //给触发器起一个名字和组名
                    .withSchedule(CronScheduleBuilder.cronSchedule(time).withMisfireHandlingInstructionDoNothing())
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteJob(String jobName) {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, JOB_GROUP_NAME));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobName, JOB_GROUP_NAME));
            scheduler.deleteJob(JobKey.jobKey(jobName, JOB_GROUP_NAME));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
