package anchor.mybatis.mq;

import anchor.mybatis.controller.CommonController;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Anchor
 * @date 2021-03-31 15:52
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = CommonController.MQ_TOPIC1, consumerGroup = MqSingleConsumer.MQ_CONSUMER_GROUP)
public class MqSingleConsumer implements RocketMQListener<String> {

    public final static String MQ_CONSUMER_GROUP = "Anchor-Consumer-Group";

    private final static AtomicInteger COUNTER = new AtomicInteger();

    public MqSingleConsumer() {
        log.info(">> MqSingleConsumer 启动成功！");
    }

    @Override
    public void onMessage(String s) {
        log.info("MqSingleConsumer received message = {}, num = {}", s, COUNTER.incrementAndGet());
    }

    @PreDestroy
    public void print(){
        log.info(">> MqSingleConsumer will self destroy.Totally consume message num = {}", COUNTER.get());
    }
}
