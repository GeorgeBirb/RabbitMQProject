package com.aiteam.springrabbitmqconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfig.QUEUE_1)
    public void listener (CustomMessage message){
        System.out.println(message);
    }
}
