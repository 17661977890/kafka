package com.example.kafka.producer;

import com.example.kafka.entity.OrderEntity;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class KafkaProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * kafka简易案例测试方法
     * @param log
     */
    public void sendLog(String log){
        System.err.println("向kafka中生产消息:"+log);
        kafkaTemplate.send("topic_log", log);

        kafkaTemplate.execute(new KafkaOperations.ProducerCallback<String, String, Object>() {
            @Override
            public Object doInKafka(Producer<String, String> producer) {
                // 这里可以编写kafka原生的api操作
                return null;
            }
        });

        // 消息发送的监听器，用于回调返回信息
        kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
            @Override
            public void onSuccess(String topic, Integer partition, String key, String value,
                                  RecordMetadata recordMetadata) {
                System.out.println("=============消息发送成功==========");

            }

            @Override
            public void onError(String topic, Integer partition, String key, String value, Exception exception) {
                System.out.println("=============消息发送失败==========");
            }

            @Override
            public boolean isInterestedInSuccess() {
                return false;
            }
        });
    }

    /**
     * 简易实战测试吞吐量：
     * 方法可以加参数，要保存得内容：
     *
     * 如果只是此处生产者发送消息，但是没有消费者监听该topic，那么发送的消息都会暂存在topic，当有消费者监听得时候，会全部将topic里得消息进行消费
     */
    public void saveByKafka(){
        OrderEntity orderEntity=new OrderEntity();
        //直接写入数据库太慢，引起超时，导致调用多次，此处需要改造成kafka异步写入。
        System.out.println("预备备！开始！");
        Timer timer = new Timer();
        timer.schedule(new MyTask(timer), 0, 2000);  //任务等待0秒后开始执行，之后每2秒执行一次
    }
    //任务：每次新建五百个下单线程。
    class MyTask extends TimerTask {
        private Timer timer;
        public MyTask(Timer timer) {
            this.timer = timer;
        }

        int second = 0;
        public void run() {
            System.out.println("~~~第"+second+"秒~~~");
            for (int i = 0; i < 500; i++) {
                AddOrder addOrder=new AddOrder();
                Thread thread=new Thread(addOrder);
                thread.start();
            }
            second++;
            if( second == 30){
                this.timer.cancel();
                System.out.println("#### 程序结束 ####");
            }
        }
    }
    //下单线程
    class AddOrder implements Runnable{
        @Override
        public void run() {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setUserId(753);
            orderEntity.setPayment(new BigDecimal(928.23));
            orderEntity.setCreateTime(new Date());
            kafkaTemplate.send("placeOrder", orderEntity.getUserId().toString(), orderEntity.toString());
        }
    }
}