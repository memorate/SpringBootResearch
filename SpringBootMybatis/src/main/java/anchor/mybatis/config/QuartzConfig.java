package anchor.mybatis.config;

import anchor.common.quartz.job.SimpleJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    private final static String JOB_IDENTITY = "AnchorSchedule";

    @Bean
    public JobDetail simpleJobDetail() {
        return JobBuilder.newJob(SimpleJob.class)
                .withIdentity(JOB_IDENTITY)
                .storeDurably()
                .build();
    }

    @Bean
    public SimpleTrigger simpleJobTrigger() {
        SimpleScheduleBuilder schedule = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(5)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(simpleJobDetail())
                .withIdentity(JOB_IDENTITY)
                .withSchedule(schedule)
                .build();
    }
}
