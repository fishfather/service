package com.jw.quartz.job;


import com.jw.quartz.repo.StuService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
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
            String jobData = (String) jobExecutionContext.getJobDetail().getJobDataMap().get("data");

            ApplicationContext applicationContext = (ApplicationContext)jobExecutionContext.getScheduler().getContext().get("appContext");

            StuService s1 = applicationContext.getBean(StuService.class);
            StuService s2 = applicationContext.getBean(StuService.class);
            System.out.println("AppContext:"+applicationContext + "||"+s1+"||"+s2);

            System.out.print("Thread is:"+Thread.currentThread().getId());
            System.out.print(" ScheduleName is:"+scheduleName);
            System.out.print(" Trigger is:"+triggerName);
            System.out.print(" jobName is:"+jobName);
            System.out.print(" jobData is:"+jobData);
            System.out.println(" execute quartz task at:"+new Date());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
