package anchor.mybatis.service.impl;

import anchor.mybatis.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

import static anchor.mybatis.constant.ScheduleConstant.*;

@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    private Scheduler scheduler;

    public Date addAndStartSimpleJob(Class<? extends Job> jobClass, Trigger trigger) throws SchedulerException {
        String jobName = jobClass.getName();
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, JOB_GROUP).storeDurably().build();
        Date date = scheduler.scheduleJob(jobDetail, trigger);
        //必须手动启动，第一次创建的scheduler处于standBy模式，不会自己启动
        scheduler.start();
        return date;
    }

    @Override
    public Date addAndStartCronJob(String className, String cronExpression) throws Exception {
        Class clazz = null;
        try {
            String trueClassName = JOB_PACKAGE + "." + className;
            clazz = Class.forName(trueClassName);
        } catch (ClassNotFoundException e) {
            throw new Exception("Class \"" + className + "\" doesn't exist.");
        }
        String triggerName = className + "Trigger";
        //将类名作为Quartz的任务名，“类名 + Trigger”作为Trigger名
        JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(className, JOB_GROUP).storeDurably().build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, TRIGGER_GROUP)
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
        Date date = scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
        return date;
    }
}
