package com.jw.quartz.controller;

import com.jw.quartz.job.QJob;
import com.jw.quartz.model.User;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    Scheduler scheduler;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public User hello(){
        System.out.println("Enter user.");
        User user = new User(18, "Jw", "M");
        return user;
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String get(){
        System.out.println("Enter get.");

        JobKey jobKey = JobKey.jobKey("testQuartzJob1");
        try {
            JobDetail jobDetail1 = scheduler.getJobDetail(jobKey);
            if(jobDetail1 !=null){
                return "Job exist";
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "Job not exist";
    }

    @RequestMapping(value = "stop", method = RequestMethod.GET)
    public String stop(){
        System.out.println("Enter stop.");

        JobKey jobKey = JobKey.jobKey("testQuartzJob1");
        try {
            JobDetail jobDetail1 = scheduler.getJobDetail(jobKey);
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "Job stop failed.";
        }
        return "Job stopped";
    }

    @RequestMapping(value = "resume", method = RequestMethod.GET)
    public String resume(){
        System.out.println("Enter resume.");

        JobKey jobKey = JobKey.jobKey("testQuartzJob1");
        try {
            JobDetail jobDetail1 = scheduler.getJobDetail(jobKey);
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "Job resume failed.";
        }
        return "Job resume";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(){
        System.out.println("Enter add.");

        JobKey jobKey = JobKey.jobKey("addJob1");
        try {
            JobDetail jobDetail1 = scheduler.getJobDetail(jobKey);
            if(jobDetail1 !=null){
                return "Job addJob1 already existed.";
            }

            jobDetail1 = JobBuilder.newJob(QJob.class).withIdentity("addJob1").storeDurably().build();

            SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever();
            Trigger trigger1  = TriggerBuilder.newTrigger().forJob(jobDetail1).withIdentity("addJob1").withSchedule(scheduleBuilder) .build();

            scheduler.scheduleJob(jobDetail1,trigger1);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "Job resume failed.";
        }
        return "Job added";
    }

    @RequestMapping(value = "addJob1", method = RequestMethod.GET)
    public String addJob1(){
        System.out.println("Enter addJob1.");

        JobKey jobKey = JobKey.jobKey("oneTimeJob");
        try {
            JobDetail jobDetail1 = scheduler.getJobDetail(jobKey);
            if(jobDetail1 !=null){
                return "Job oneTimeJob already existed.";
            }

            jobDetail1 = JobBuilder.newJob(QJob.class).withIdentity("oneTimeJob").storeDurably().build();

            SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule();//.withIntervalInSeconds(3).withRepeatCount(0);
            Trigger trigger1  = TriggerBuilder.newTrigger().forJob(jobDetail1).withIdentity("oneTimeJob")
                    .withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail1,trigger1);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "Job added failed.";
        }
        return "Job added";
    }

    @RequestMapping(value = "getJob1", method = RequestMethod.GET)
    public String getJob1(){
        System.out.println("Enter getJob1.");

        JobKey jobKey = JobKey.jobKey("oneTimeJob");
        Trigger.TriggerState state = null;
        try {
            JobDetail jobDetail1 = scheduler.getJobDetail(jobKey);
            if(jobDetail1 !=null){
                System.out.println( "Job oneTimeJob already existed.");
            }

            Trigger oneTimeJob = scheduler.getTrigger(TriggerKey.triggerKey("oneTimeJob"));
            if(oneTimeJob !=null){
                System.out.println(" Trigger onetimeJob existed.");
            }

            state = scheduler.getTriggerState(TriggerKey.triggerKey("oneTimeJob"));
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "Job resume failed.";
        }
        return "Job trigger state "+state;
    }
}
