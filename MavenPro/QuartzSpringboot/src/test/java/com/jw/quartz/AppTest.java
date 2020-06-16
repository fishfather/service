package com.jw.quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class AppTest {
    @Autowired
    Scheduler scheduler;

    @Test
    public void testUpdateTriggerCronExpression() throws SchedulerException, InterruptedException {
        System.out.println("First test"+scheduler);

        Set<TriggerKey> triggerKeys = null;
        triggerKeys = scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals("GROUP_JW"));
        for(TriggerKey key: triggerKeys){
            CronTrigger trigger = (CronTrigger)scheduler.getTrigger(key);
            System.out.println("The cron is:"+trigger.getCronExpression());
            if("0/20 * * * * ?".equals(trigger.getCronExpression())){
                System.out.println("FIND same trigger............................");
            }
            if(key.getName().equals("FNC")){
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();// 触发器
                triggerBuilder.withIdentity(key);// 触发器名,触发器组
//                triggerBuilder.startNow();// 立即执行
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?"));// 触发器时间设定
                trigger = (CronTrigger) triggerBuilder.build();// 创建Trigger对象
                scheduler.rescheduleJob(key, trigger);
            }
        }

        TimeUnit.SECONDS.sleep(30);
    }

    @Test
    public void deleteJob() throws Exception {
        JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey("ARTISAN", "GROUP_JW"));
        System.out.println("Job detail:"+jobDetail);
        boolean b = scheduler.deleteJob(JobKey.jobKey("testQuartzJob1", "group1"));
        System.out.println("deleted job:"+b);
    }
}
