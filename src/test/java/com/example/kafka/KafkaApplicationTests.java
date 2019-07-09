package com.example.kafka;

import com.example.kafka.producer.KafkaProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaApplicationTests {
	@Autowired
	private KafkaProducer kafkaProducer;

	/**
	 * ================================测试2=======================
	 */
	@Test
	public void contextLoads() {
		kafkaProducer.sendLog("测试数据");
	}

}
