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

    @KafkaListener(topics = {"topic_log"})
     public void listen (ConsumerRecord<?, ?> record, Acknowledgment acknowledgment, Consumer<?, ?> consumer){
		try {
			String logStr = (String) record.value();
			acknowledgment.acknowledge();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("kafka消息消费失败", e);
		}
     }
}