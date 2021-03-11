package anchor.mybatis.controller;

import anchor.common.response.BaseResponse;
import anchor.mybatis.quartz.job.SimpleJob;
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

import static anchor.mybatis.object.constant.ScheduleConstant.TRIGGER_GROUP;


@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Resource
    private ScheduleService scheduleService;

    @GetMapping("/addSimpleJob")
    public BaseResponse<Date> addSimpleJob() throws SchedulerException {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)
                .withMisfireHandlingInstructionFireNow()
                .repeatForever();
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("SimpleTrigger", TRIGGER_GROUP)
                .startNow()
                .withSchedule(scheduleBuilder).build();
        Date date = scheduleService.addAndStartSimpleJob(SimpleJob.class, trigger);
        return new BaseResponse<>(date);
    }

    @GetMapping("/addCronJob")
    public BaseResponse<Date> addCronJob(@RequestParam String className, @RequestParam String cronExpression) throws Exception {
        Date date = scheduleService.addAndStartCronJob(className, cronExpression);
        return new BaseResponse<>(date);
    }

    @GetMapping("/modifyJobCron")
    public BaseResponse<Date> modifyJobCron(@RequestParam String className, @RequestParam String cronExpression) throws Exception {
        Date date = scheduleService.modifyJobCron(className, cronExpression);
        return new BaseResponse<>(date);
    }

    @GetMapping("/pauseJob")
    public BaseResponse pauseJob(@RequestParam String className) throws Exception {
        scheduleService.pauseJob(className);
        return new BaseResponse();
    }

    @GetMapping("/resumeJob")
    public BaseResponse resumeJob(@RequestParam String className) throws Exception {
        scheduleService.resumeJob(className);
        return new BaseResponse();
    }

    @GetMapping("/pauseAll")
    public BaseResponse pauseAll() throws SchedulerException {
        scheduleService.pauseAll();
        return new BaseResponse();
    }

    @GetMapping("/resumeAll")
    public BaseResponse resumeAll() throws SchedulerException {
        scheduleService.resumeAll();
        return new BaseResponse();
    }

    @GetMapping("/getJobsByState")
    public BaseResponse<List<String>> getJobsByState(@RequestParam String queryState) throws Exception {
        return new BaseResponse<>(scheduleService.getJobsByState(queryState));
    }

    @GetMapping("/deleteJob")
    public BaseResponse<Boolean> deleteJob(@RequestParam String className) throws Exception {
        return new BaseResponse<>(scheduleService.deleteJob(className));
    }

    @GetMapping("/getJobStatus")
    public BaseResponse<String> getJobStatus(@RequestParam String className) throws Exception {
        return new BaseResponse<>(scheduleService.getJobStatus(className));
    }
}
