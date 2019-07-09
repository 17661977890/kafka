package com.example.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaApplication /**implements CommandLineRunner*/{

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}


	/**
	 * ==============================测试1：启动即打印===================================
	 */

//	@Autowired
//	private KafkaTemplate kafkaTemplate;
//
//	@Override
//	public void run(String... args) throws Exception {
//		kafkaTemplate.send("ooxx","this is a message");
//	}
//
//	@KafkaListener(topics = "ooxx", groupId = "test-consumer-group")
//	public void listener(String message) {
//
//		System.out.println(">>>>>" + message);
//	}
}
