package com.jw.quartz;

import com.jw.quartz.job.QJob;
import com.jw.quartz.manager.QuartzManager;
import com.jw.quartz.model.Job;
import com.jw.quartz.repo.StuService;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MyStartupRunner implements CommandLineRunner {
    @Autowired
    QuartzManager manager;

    @Autowired
    Scheduler scheduler;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>MyStartupRunner2 start<<<<<<<<<<<<<" + scheduler.getSchedulerName());

        scheduler.start();

        manager.updateAllQuartzJobs();

//        try {
//            List<String> jobGroupNames = scheduler.getJobGroupNames();
//            System.out.println("Groups--------------------------------:"+jobGroupNames);
//
//
//            TriggerKey triggerKey = TriggerKey.triggerKey("testQuartzTriggerA", "DEFAULT");
//            Set<TriggerKey> group1 = scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals("DEFAULT"));
//            System.out.println("----------------------:"+group1);
//
//            Set<JobKey> group11 = scheduler.getJobKeys(GroupMatcher.jobGroupEquals("group1"));
//
//            scheduler.pauseTriggers(GroupMatcher.triggerGroupEquals("DEFAULT")); // 停止触发器
//            boolean bb = scheduler.unscheduleJobs(new ArrayList<>(group1)); // 移除触发器
//            System.out.println("final:"+bb);
//
//            scheduler.deleteJobs(new ArrayList<>(group11)) ;     //删除job
//
////            scheduler.rescheduleJob()
////            scheduler.getTriggersOfJob()
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
    }


}
