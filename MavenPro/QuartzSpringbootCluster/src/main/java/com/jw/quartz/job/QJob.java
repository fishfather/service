package com.jw.quartz.job;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

public class QJob extends QuartzJobBean {
    /**
     * 执行定时任务 * * @param jobExecutionContext * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            String triggerName = jobExecutionContext.getTrigger().getKey().getName();
            String jobName = jobExecutionContext.getTrigger().getJobKey().getName();
            String scheduleName = jobExecutionContext.getScheduler().getSchedulerName();

            System.out.print("Thread is:"+Thread.currentThread().getId());
            System.out.print(" ScheduleName is:"+scheduleName);
            System.out.print(" Trigger is:"+triggerName);
            System.out.print(" jobName is:"+jobName);
            System.out.println(" execute quartz task at:"+new Date());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
