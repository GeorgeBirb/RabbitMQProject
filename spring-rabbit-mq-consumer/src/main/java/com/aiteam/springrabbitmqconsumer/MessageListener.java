package com.aiteam.springrabbitmqconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class MessageListener {

    @Autowired
    private RabbitTemplate template;

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener (CustomMessage message){
        System.out.println(message);
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessageDate(new Date());
        message.setMessage("This message is the response!");
        template.convertAndSend(MQConfig.EXCHANGE_1,
                MQConfig.ROUTING_KEY_1, message);
        template.convertAndSend(MQConfig.EXCHANGE_2,
                MQConfig.ROUTING_KEY_2, message);
        System.out.println("Response sent to Producer and to Consumer1!");
    }
}
