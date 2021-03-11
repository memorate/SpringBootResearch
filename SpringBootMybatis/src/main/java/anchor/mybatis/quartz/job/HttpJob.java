package anchor.mybatis.quartz.job;

import anchor.mybatis.object.vo.MobileResponse;
import anchor.mybatis.service.CommonService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HttpJob extends QuartzJobBean {

    @Resource
    private CommonService commonService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        MobileResponse response = commonService.getForResponse("15927005006");
        MobileResponse.Data data = response.getData();
        if (data != null && !data.getProvince().isEmpty()) {
            System.out.println("Execute \"HttpJob\" success.Data = \"" + data + "\".Time = \"" +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) + "\"");
        } else {
            System.out.println("Executed \"HttpJob\",but something went wrong.Time = \"" +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) + "\"");
        }
    }
}
