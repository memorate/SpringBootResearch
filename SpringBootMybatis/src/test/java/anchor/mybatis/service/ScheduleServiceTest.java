package anchor.mybatis.service;

import anchor.mybatis.quartz.job.CronJob;
import anchor.mybatis.quartz.job.SimpleJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleServiceTest {

    @Resource
    private Scheduler scheduler;

    @Test
    public void sameJob() throws SchedulerException, InterruptedException {
        JobDetail simpleJob = JobBuilder.newJob(SimpleJob.class)        //传入一个Job类
                .withIdentity("SimpleJob", "AnchorJobs")    //(name, group)标识唯一一个JobDetail
                .storeDurably()        //在没有Trigger关联的情况下保存该任务到调度器
                .build();

        JobDetail cronJob = JobBuilder.newJob(CronJob.class)        //传入一个Job类
                .withIdentity("CronJob", "AnchorJobs")    //(name, group)标识唯一一个JobDetail
                .storeDurably()        //在没有Trigger关联的情况下保存该任务到调度器
                .build();
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(5)      //每5秒执行一次
                .repeatForever();              //无限循环执行
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity("SimpleTrigger", "AnchorTriggers")    //(name, group)唯一标识一个Trigger
                .startNow()
                .withSchedule(scheduleBuilder)
                .build();
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("CronTrigger", "AnchorTriggers")    //(name, group)唯一标识一个Trigger
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))      //不同的scheduleBuilder
                .build();
        scheduler.scheduleJob(simpleJob, simpleTrigger);
//        scheduler.scheduleJob(cronJob, simpleTrigger);
        Thread.sleep(30000);
    }
}
