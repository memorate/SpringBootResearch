package anchor.mybatis.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
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
        // 每次拉100条
        consumer.setPullBatchSize(100);
        // 2秒拉一次
        consumer.setPullInterval(2000);
        consumer.setConsumeMessageBatchMaxSize(100);
        try {
            consumer.subscribe(MQ_TOPIC2, "*");
            consumer.registerMessageListener(new BatchMessageListener());
            consumer.start();
        } catch (MQClientException e) {
            log.error("MqBatchConsumer error.", e);
        }
    }

    @PreDestroy
    public void print() {
        log.info(">> MqBatchConsumer will self destroy.Totally consume message num = {}", COUNTER.get());
    }

    private static class BatchMessageListener implements MessageListenerConcurrently {
        @Override
        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
            try {
                MessageQueue messageQueue = context.getMessageQueue();
                String msgFrom = messageQueue.getBrokerName() + "-" + messageQueue.getQueueId();
                msgs.forEach(t -> {
                    log.info("MqBatchConsumer receive msg from = {}, msg = {}", msgFrom, new String(t.getBody()));
                    COUNTER.incrementAndGet();
                });
                log.info("-- MqBatchConsumer consumed once finished. current list size = {}, total consume size = {}", msgs.size(), COUNTER.get());
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            } catch (Exception e) {
                log.error("BatchMessageListener error, ", e);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }
    }
}
