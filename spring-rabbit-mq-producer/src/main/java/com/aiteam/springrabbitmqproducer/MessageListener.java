package com.aiteam.springrabbitmqproducer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfig.QUEUE_2)
    public void listener (CustomMessage message){
        System.out.println(message);
    }
}
