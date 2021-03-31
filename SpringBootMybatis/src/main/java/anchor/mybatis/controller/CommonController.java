package anchor.mybatis.controller;

import anchor.common.response.BaseResponse;
import anchor.mybatis.base.AnchorBean;
import anchor.mybatis.base.aop.ResultRecorder;
import anchor.mybatis.base.validation.InsertGroup;
import anchor.mybatis.object.vo.MobileResponse;
import anchor.mybatis.object.vo.Validation;
import anchor.mybatis.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Anchor
 */
@Slf4j
@ResultRecorder("公共资源")
@RestController
@RequestMapping("/common")
public class CommonController implements InitializingBean {
    @Resource
    private CommonService commonService;

    @Resource
    private AnchorBean anchorBean;

    @Resource
    private RocketMQTemplate rocketMqTemplate;

    @Resource
    private ThreadPoolTaskExecutor executor;

    public final static String MQ_TOPIC1 = "Anchor-Topic1";
    public final static String MQ_TOPIC2 = "Anchor-Topic2";
    private static final AtomicInteger COUNTER1 = new AtomicInteger();
    private static final AtomicInteger COUNTER2 = new AtomicInteger();

    @Override
    public void afterPropertiesSet() throws Exception {
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(200);
    }

    @PreDestroy
    public void print(){
        log.info(">> Producer1 produce msg num = {}", COUNTER1.get());
        log.info(">> Producer2 produce msg num = {}", COUNTER2.get());
    }

    @GetMapping("/usersExcel")
    public void exportUsers() {
        commonService.exportAllUsers();
    }

    @GetMapping("/getForString")
    public String sendGetForString(@RequestParam String mobile) {
        return commonService.getForString(mobile);
    }

    @GetMapping("/getForResponse")
    public MobileResponse sendGetForResponse(@RequestParam String mobile) {
        return commonService.getForResponse(mobile);
    }

    @GetMapping("/exception")
    public BaseResponse<String> exceptionTest(@RequestParam String param, @RequestParam int type, @RequestHeader String header) {
        commonService.exceptionTest(param, type);
        return new BaseResponse<>("Success!");
    }

    @GetMapping("/aopTest")
    public BaseResponse<Boolean> aopTest(String aa, String bb) {
        return new BaseResponse<>(commonService.aopTest());
    }

    @GetMapping
    public BaseResponse<Boolean> annotationTest() {
        log.info("Executing annotationTest()...");
        return new BaseResponse<>(Boolean.TRUE);
    }

    @PostMapping("/validate")
    public BaseResponse<Boolean> validate(@Validated(InsertGroup.class) @RequestBody Validation vo) {
        log.info(vo.toString());
        return new BaseResponse<>(Boolean.TRUE);
    }

    @GetMapping("asyn")
    public BaseResponse<Boolean> asyncRequest() {
        return new BaseResponse<>(Boolean.TRUE);
    }

    @GetMapping("anchorBean")
    public BaseResponse<String> getAnchorBeanParam() {
        return new BaseResponse<>(anchorBean.getParam());
    }

    @GetMapping("mq1")
    public BaseResponse<String> produceMsg1() {
        int eachSize = 1000, threadSize = 20;
        for (int i = 0; i < threadSize; i++) {
            executor.execute(() -> {
                for (int j = 0; j < eachSize; j++) {
                    rocketMqTemplate.sendOneWay(MQ_TOPIC1, MessageBuilder.withPayload("Anchor-Message-1-" + j).build());
                    COUNTER1.incrementAndGet();
                }
            });
        }
        return new BaseResponse<>("Success!");
    }

    @GetMapping("mq2")
    public BaseResponse<String> produceMsg2() {
        final int eachSize = 1000, threadSize = 20;
        for (int i = 0; i < threadSize; i++) {
            executor.execute(() -> {
                for (int j = 0; j < eachSize; j++) {
                    rocketMqTemplate.sendOneWay(MQ_TOPIC2, MessageBuilder.withPayload("Anchor-Message-2-" + j).build());
                    COUNTER2.incrementAndGet();
                }
            });
        }
        return new BaseResponse<>("Success!");
    }
}
