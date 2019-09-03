package springboot.HelloQuartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class HelloScheduler {
	public static void main(String[] args) throws SchedulerException {
		// 创建一个jobdetail实例，将与hellojob绑定
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
				.withIdentity("myJob")
				.usingJobData("message", "success")
				.build();
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("myTrigger", "group1")
				.usingJobData("message", 1).startNow()
				.withSchedule(SimpleScheduleBuilder
						.simpleSchedule()
						.withIntervalInSeconds(20)
						.repeatForever())
						.build();
		// 创建scheduler实例
		SchedulerFactory sfact = new StdSchedulerFactory();
		Scheduler scheduler = sfact.getScheduler();
		scheduler.start();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("当前时间是：" + sdf.format(date));
		scheduler.scheduleJob(jobDetail, trigger);
	}

}
