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
                .repeatForever();
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("SimpleTrigger", TRIGGER_GROUP)
                .startNow()
                .withSchedule(scheduleBuilder).build();
        return scheduleService.addJob(SimpleJob.class, trigger);
    }

    @GetMapping("/startJob")
    public void startJob(@RequestParam String className) throws Exception {
        scheduleService.startJob(className);
    }
}
