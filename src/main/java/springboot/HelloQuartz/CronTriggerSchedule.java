package springboot.HelloQuartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class CronTriggerSchedule {

	public static void main(String[] args) throws SchedulerException, InterruptedException {
		
		// 创建一个jobdetail实例，将与hellojob绑定
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
				.withIdentity("myJob")
				.usingJobData("message", "success")
				.build();
		
		//创建触发器，触发job的执行
		CronTrigger trigger = (CronTrigger) TriggerBuilder
				.newTrigger()
				.withIdentity("myTrigger", "group1")
				.usingJobData("message", 1)
				.withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))
				.build();
		
		// 创建scheduler实例
		SchedulerFactory sfact = new StdSchedulerFactory();
		Scheduler scheduler = sfact.getScheduler();
		scheduler.start();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("当前时间是：" + sdf.format(date));
		scheduler.scheduleJob(jobDetail, trigger);
		//两秒后挂起
		Thread.sleep(2000L);
//		scheduler.standby();
		scheduler.shutdown();
		
		Thread.sleep(3000L);
		scheduler.start();
	}


}
