package com.example.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class KafkaConsumer {


	/**
	 * 接收消息直接用@KafkaListener注解即可，并在监听中设置监听的topic，topics是一个数组所以是可以绑定多个主题的，
	 * 如@KafkaListener(topics = {“topicA”,“topicB”})。
	 * 这里的topic需要和消息发送类 KafkaSender.java中设置的topic一致。
	 *
	 * 这里消费逻辑可以加入操作数据库的逻辑，把生产者那边监听得数据进行持久化
	 * @param record
	 * @param acknowledgment
	 * @param consumer
	 */
    @KafkaListener(topics = {"topic_log","placeOrder"})
     public void listen (ConsumerRecord<?, ?> record, Acknowledgment acknowledgment, Consumer<?, ?> consumer){
		try {
			String logStr = (String) record.value();
			log.info("kafka消费成功："+logStr);
			acknowledgment.acknowledge();
			//todo 保存数据持久化得相关业务代码--OrderEntity 比如有该实体对应的表，就可以进行异步保存了

		} catch (Exception e) {
			e.printStackTrace();
			log.error("kafka消息消费失败", e);
		}
     }
}