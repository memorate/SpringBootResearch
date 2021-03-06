package anchor.mybatis.service;

import org.quartz.Job;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import java.util.Date;
import java.util.List;

/**
 * @author Anchor
 */
public interface ScheduleService {

    Date addAndStartSimpleJob(Class<? extends Job> jobClass, Trigger trigger) throws SchedulerException;

    Date addAndStartCronJob(String className, String cronExpression) throws Exception;

    Date modifyJobCron(String className, String cronExpression) throws Exception;

    void pauseJob(String className) throws Exception;

    void resumeJob(String className) throws Exception;

    void pauseAll() throws SchedulerException;

    void resumeAll() throws SchedulerException;

    List<String> getJobsByState(String queryState) throws Exception;

    boolean deleteJob(String className) throws Exception;

    String getJobStatus(String className) throws Exception;
}
