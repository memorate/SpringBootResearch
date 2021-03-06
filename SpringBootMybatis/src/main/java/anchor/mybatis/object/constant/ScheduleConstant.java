package anchor.mybatis.object.constant;

import com.google.common.collect.ImmutableMap;
import org.quartz.Trigger;

import java.util.Map;

/**
 * @author Anchor
 */
public interface ScheduleConstant {
    String JOB_GROUP = "AnchorJobs";
    String TRIGGER_GROUP = "AnchorTriggers";
    String JOB_PACKAGE = "anchor.mybatis.quartz.job";

    //主要用于返回给接口调用者
    Map<Trigger.TriggerState, String> JOB_STATUS = ImmutableMap.of(
            Trigger.TriggerState.NONE, "定时任务不存在",
            Trigger.TriggerState.NORMAL, "定时任务正常运行",
            Trigger.TriggerState.PAUSED, "定时任务已暂停",
            Trigger.TriggerState.BLOCKED, "定时任务阻塞中",
            Trigger.TriggerState.COMPLETE, "定时任务已完成"
    );

    //用于内部状态判断
    Map<String, Trigger.TriggerState> TRIGGER_STATUS = ImmutableMap.of(
            "NONE", Trigger.TriggerState.NONE,
            "NORMAL", Trigger.TriggerState.NORMAL,
            "PAUSED", Trigger.TriggerState.PAUSED,
            "BLOCKED", Trigger.TriggerState.BLOCKED,
            "COMPLETE", Trigger.TriggerState.COMPLETE
    );
}
