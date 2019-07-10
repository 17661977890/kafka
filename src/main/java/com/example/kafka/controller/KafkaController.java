package com.example.kafka.controller;

import com.example.kafka.producer.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 彬
 * @Date 2019/7/10
 */
@RestController
@Slf4j
public class KafkaController {
    @Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping(value = "/kafkaSend")
    public void kafkaSend(String log){
        kafkaProducer.sendLog(log);
    }
}
