package com.novellatonyatt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author: Zhuang HaoTang
 * @create: 2020-06-01 11:34
 * @description:
 */
@Component
public class Main {

    @KafkaListener(groupId = "GroupA", topics = "goods", concurrency = "3")
    public void consumerMessage(String value) {
        System.out.println(String.format("消费消息,value:%s", value));
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring-context.xml");
        while(true){}
    }

}
