package com.fulinhu.test;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.DirectSchedulerFactory;

public class Quarz implements Job{

	public static void main(String[] args) throws SchedulerException {
		JobDetail detail=JobBuilder.newJob(Quarz.class).withIdentity("myJob", "group").build();
	    // 一个scheduler 可以绑定多个job
        // 一个 Job 在同一个 Scheduler 实例中通过名称和组名能唯一被标识
        // 每5秒执行一次,共执行四次(注意参数为3)
        SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5)
                .withRepeatCount(3);
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger_1", "tGroup1").startNow()
                .withSchedule(builder).build();
		
		/*SimpleTrigger trigger=(SimpleTrigger) TriggerBuilder.newTrigger()
        		        .withIdentity("myTrigger", "group1")
        		        .startNow()
        		        .withSchedule(
        		        	SimpleScheduleBuilder.simpleSchedule()
        		        	.withIntervalInSeconds(2).repeatForever());*/
        DirectSchedulerFactory factory=DirectSchedulerFactory.getInstance();
        factory.createVolatileScheduler(10);//创建线程数
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
        Date date=new Date();
        String format = DateFormatUtils.format(date, "yyyy-MM-dd hh:mm:ss");
        System.out.println("START"+"_____"+format);
        scheduler.scheduleJob(detail, trigger);
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		Date date=new Date();
		String format = DateFormatUtils.format(date, "yyyy-MM-dd hh:mm:ss");
		System.out.println("job"+"_____"+format);
	}

}
