package springboot.HelloQuartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

public class HelloJob implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {
		//打印当前时间，格式为 年月日时分秒
		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("当前时间是："+sdf.format(date));
		JobKey key = context.getJobDetail().getKey();
		System.out.println(key.getName()+","+key.getGroup());
		TriggerKey triKey = context.getTrigger().getKey();
		System.out.println(triKey.getName()+","+triKey.getGroup());
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		JobDataMap tDataMap = context.getTrigger().getJobDataMap();
		String jobMsg = dataMap.getString("message");
		Integer tjobMsg = tDataMap.getInt("message");
		System.out.println(jobMsg +"," + tjobMsg);
	}

}
