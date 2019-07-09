package com.example.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;
 
    public void sendLog(String log){
        System.err.println("向kafka中生产消息:"+log);
        kafkaTemplate.send("topic_log", log);
    }
}