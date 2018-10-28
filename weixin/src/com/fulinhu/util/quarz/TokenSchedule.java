package com.fulinhu.util.quarz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.DirectSchedulerFactory;

import com.fulinhu.test.Quarz;

public class TokenSchedule {
	public static void runTokenJob(String appid,String appsecret) throws SchedulerException{
		JobDetail detail=JobBuilder.newJob(TokenJob.class).withIdentity("myJob", "group").
				usingJobData("appid", appid).usingJobData("appsecret", appsecret).build();
		SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule().
				withIntervalInSeconds(7200).repeatForever();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger_1", "tGroup1").startNow()
				.withSchedule(builder).build();
		DirectSchedulerFactory factory=DirectSchedulerFactory.getInstance();
		factory.createVolatileScheduler(10);
		Scheduler scheduler = factory.getScheduler();
	    scheduler.start();
	    scheduler.scheduleJob(detail, trigger);
	}
}
