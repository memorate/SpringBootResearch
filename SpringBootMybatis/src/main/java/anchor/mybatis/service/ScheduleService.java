package anchor.mybatis.service;

import org.quartz.Job;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import java.util.Date;

public interface ScheduleService {

    void startJob(String className) throws Exception;

    Date addJob(Class <? extends Job> jobClass, Trigger trigger) throws SchedulerException;
}
