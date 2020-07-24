package anchor.mybatis.service.impl;

import anchor.mybatis.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

import static anchor.mybatis.constant.ScheduleConstant.JOB_GROUP;

@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    private Scheduler scheduler;

    @Override
    public void startJob(String className) throws Exception {
        JobKey jobKey = new JobKey(className, JOB_GROUP);
        boolean exist = scheduler.checkExists(jobKey);
        if (exist) {
            scheduler.triggerJob(jobKey);
        } else {
            throw new Exception("Job doesn't exist.");
        }
    }

    public Date addJob(Class<? extends Job> jobClass, Trigger trigger) throws SchedulerException {
        String jobName = jobClass.getName();
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, JOB_GROUP).storeDurably().build();
        Date date = scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
        return date;
    }
}
