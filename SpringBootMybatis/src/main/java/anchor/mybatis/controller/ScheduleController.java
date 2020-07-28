package anchor.mybatis.controller;

import anchor.common.quartz.job.SimpleJob;
import anchor.mybatis.service.ScheduleService;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static anchor.mybatis.constant.ScheduleConstant.TRIGGER_GROUP;


@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Resource
    private ScheduleService scheduleService;

    @GetMapping("/addSimpleJob")
    public Date addSimpleJob() throws SchedulerException {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)
                .withMisfireHandlingInstructionFireNow()
                .repeatForever();
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("SimpleTrigger", TRIGGER_GROUP)
                .startNow()
                .withSchedule(scheduleBuilder).build();
        return scheduleService.addAndStartSimpleJob(SimpleJob.class, trigger);
    }

    @GetMapping("/addCronJob")
    public Date addCronJob(@RequestParam String className, @RequestParam String cronExpression) throws Exception {
        return scheduleService.addAndStartCronJob(className, cronExpression);
    }

    @GetMapping("/modifyJobCron")
    public Date modifyJobCron(@RequestParam String className, @RequestParam String cronExpression) throws Exception {
        return scheduleService.modifyJobCron(className, cronExpression);
    }

    @GetMapping("/pauseJob")
    public void pauseJob(@RequestParam String className) throws Exception {
        scheduleService.pauseJob(className);
    }

    @GetMapping("/resumeJob")
    public void resumeJob(@RequestParam String className) throws Exception {
        scheduleService.resumeJob(className);
    }

    @GetMapping("/pauseAll")
    public void pauseAll() throws SchedulerException {
        scheduleService.pauseAll();
    }

    @GetMapping("/resumeAll")
    public void resumeAll() throws SchedulerException {
        scheduleService.resumeAll();
    }

    @GetMapping("/getExecutingJobs")
    public List<String> getExecutingJobs() throws SchedulerException {
        return scheduleService.getExecutingJobs();
    }

    @GetMapping("/deleteJob")
    public boolean deleteJob(@RequestParam String className) throws Exception {
        return scheduleService.deleteJob(className);
    }
}
