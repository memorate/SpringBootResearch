package anchor.mybatis.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Anchor
 */
public class SimpleJob extends QuartzJobBean {

    private final static AtomicInteger counter = new AtomicInteger(1);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LocalDateTime now = LocalDateTime.now();
        String name = Thread.currentThread().getName();
        System.out.println("Execute quartz \"SimpleJob\", threadName = \"" + name +
                "\", the " + counter.getAndIncrement() + "th execution, time = \"" +
                now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) + "\"");
    }
}
