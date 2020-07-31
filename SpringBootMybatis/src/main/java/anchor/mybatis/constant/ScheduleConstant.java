package anchor.mybatis.constant;

import com.google.common.collect.ImmutableMap;
import org.quartz.Trigger;

import java.util.Map;

public interface ScheduleConstant {
    String JOB_GROUP = "AnchorJobs";
    String TRIGGER_GROUP = "AnchorTriggers";
    String JOB_PACKAGE = "anchor.mybatis.quartz.job";

    Map<Trigger.TriggerState, String> JOB_STATUS = ImmutableMap.of(
            Trigger.TriggerState.NONE, "定时任务不存在",
            Trigger.TriggerState.NORMAL, "定时任务正常运行",
            Trigger.TriggerState.PAUSED, "定时任务已暂停",
            Trigger.TriggerState.BLOCKED, "定时任务阻塞中",
            Trigger.TriggerState.COMPLETE, "定时任务已完成"
    );
}
