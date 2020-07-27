package anchor.mybatis.service;

import org.quartz.Job;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import java.util.Date;

public interface ScheduleService {

    Date addAndStartSimpleJob(Class <? extends Job> jobClass, Trigger trigger) throws SchedulerException;

    Date addAndStartCronJob(String className, String cronExpression) throws Exception;
}
