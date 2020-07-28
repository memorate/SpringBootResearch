package anchor.mybatis.service.impl;

import anchor.mybatis.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static anchor.mybatis.constant.ScheduleConstant.*;

@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    private Scheduler scheduler;

    public Date addAndStartSimpleJob(Class<? extends Job> jobClass, Trigger trigger) throws SchedulerException {
        String jobName = jobClass.getName();
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, JOB_GROUP).storeDurably().build();
        return scheduler.scheduleJob(jobDetail, trigger);
    }

    @Override
    public Date addAndStartCronJob(String className, String cronExpression) throws Exception {
        Class clazz = null;
        try {
            //用于校验类名是否存在并实例化一个自定义的Job类
            String entireClassName = JOB_PACKAGE + "." + className;
            clazz = Class.forName(entireClassName);
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
        return scheduler.scheduleJob(jobDetail, trigger);
    }

    @Override
    public Date modifyJobCron(String className, String cronExpression) throws Exception {
        try {
            //用于校验类名是否存在
            String entireClassName = JOB_PACKAGE + "." + className;
            Class clazz = Class.forName(entireClassName);
        } catch (ClassNotFoundException e) {
            throw new Exception("Class \"" + className + "\" doesn't exist.");
        }
        String triggerName = className + "Trigger";
        TriggerKey triggerKey = new TriggerKey(triggerName, TRIGGER_GROUP);
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
        return scheduler.rescheduleJob(triggerKey, trigger);
    }

    @Override
    public void pauseJob(String className) throws Exception {
        JobKey jobKey = new JobKey(className, JOB_GROUP);
        if (scheduler.checkExists(jobKey)) {
            scheduler.pauseJob(jobKey);
        } else {
            throw new Exception("Job \"" + className + "\" doesn't exist.");
        }
    }

    @Override
    public void resumeJob(String className) throws Exception {
        JobKey jobKey = new JobKey(className, JOB_GROUP);
        if (scheduler.checkExists(jobKey)) {
            scheduler.resumeJob(jobKey);
        } else {
            throw new Exception("Job \"" + className + "\" doesn't exist.");
        }
    }

    @Override
    public void pauseAll() throws SchedulerException {
        scheduler.pauseAll();
    }

    @Override
    public void resumeAll() throws SchedulerException {
        scheduler.resumeAll();
    }

    @Override
    public List<String> getExecutingJobs() throws SchedulerException {
        List<String> jobNames = new ArrayList<>();
        List<String> jobGroupNames = scheduler.getJobGroupNames();
        for (String groupName : jobGroupNames) {
            for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                jobNames.add(jobKey.getName());
            }
        }
        return jobNames;
    }

    @Override
    public boolean deleteJob(String className) throws Exception {
        JobKey jobKey = new JobKey(className, JOB_GROUP);
        if (scheduler.checkExists(jobKey)) {
            return scheduler.deleteJob(jobKey);
        } else {
            throw new Exception("Job \"" + className + "\" doesn't exist.");
        }
    }
}
