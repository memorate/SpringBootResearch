package anchor.mybatis.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.atomic.AtomicInteger;

import static anchor.mybatis.controller.CommonController.MQ_TOPIC2;
import static anchor.mybatis.mq.MqSingleConsumer.MQ_CONSUMER_GROUP;

/**
 * @author Anchor
 * @date 2021-03-31 17:50
 */
@Slf4j
@Component
public class MqBatchConsumer {

    @Value("${rocketmq.name-server}")
    private String nameServ;

    private final static AtomicInteger COUNTER = new AtomicInteger();

    public MqBatchConsumer() {
        log.info(">> MqBatchConsumer 启动成功！");
    }

    @PostConstruct
    public void batchConsume() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MQ_CONSUMER_GROUP);
        consumer.setNamesrvAddr(nameServ);
        consumer.setMessageModel(MessageModel.CLUSTERING);
        // 一个新的订阅组第一次启动从队列的最后位置开始消费, 后续再启动接着上次消费的进度开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.setConsumeThreadMin(1);
        consumer.setConsumeThreadMax(10);
        consumer.setPullBatchSize(100);
        consumer.setPullInterval(2000);
        try {
            consumer.subscribe(MQ_TOPIC2, "*");
            consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                msgs.forEach(t -> log.info("MqBatchConsumer received message = {}", new String(t.getBody())));
                log.info("-- MqBatchConsumer received once end. size = {}", COUNTER.accumulateAndGet(msgs.size(), Integer::sum));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
        } catch (MQClientException e) {
            log.error("MqBatchConsumer error.", e);
        }
    }

    @PreDestroy
    public void print(){
        log.info(">> MqBatchConsumer will self destroy.Totally consume message num = {}", COUNTER.get());
    }
}
