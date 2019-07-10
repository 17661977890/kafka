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

    /**
     * 测试kafka 简易demo
     * @param log
     */
    @RequestMapping(value = "/kafkaSend")
    public void kafkaSend(String log){
        kafkaProducer.sendLog(log);
    }

    /**
     * 测试kafka 得吞吐量（利用线程）
     */
    @RequestMapping(value = "kafkaSend2")
    public void kafkaSend2(){
        kafkaProducer.saveByKafka();
    }
}
