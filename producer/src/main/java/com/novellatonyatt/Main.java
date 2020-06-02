package com.novellatonyatt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author: Zhuang HaoTang
 * @create: 2020-06-01 11:34
 * @description:
 */
@Component
public class Main {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 同一个时间戳(key)，发送3条消息
     * 每调用一次方法发300条消息
     */
    private void sendMessage() throws InterruptedException {
        int i = 0;
//        while(i<100){
        while(true){
            String key = String.valueOf(System.currentTimeMillis());
            kafkaTemplate.send("goods", key, UUID.randomUUID().toString());
            kafkaTemplate.send("goods", key, UUID.randomUUID().toString());
            kafkaTemplate.send("goods", key, UUID.randomUUID().toString());
            i++;
            System.out.println(String.format("num:%s,key:%s,发送成功",i,key));
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring-context.xml");
        Main main = applicationContext.getBean(Main.class);
        main.sendMessage();
    }

}
