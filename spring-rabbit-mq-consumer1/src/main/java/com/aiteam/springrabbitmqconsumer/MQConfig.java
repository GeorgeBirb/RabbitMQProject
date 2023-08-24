package com.aiteam.springrabbitmqconsumer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String QUEUE_1 = "message_queue_1";
    public static final String EXCHANGE_1 = "message_exchange_1";
    public static final String ROUTING_KEY_1 = "message_routingKey_1";


    @Bean
    public Queue queue1(){
        return new Queue(QUEUE_1);
    }

    @Bean
    public TopicExchange exchange1(){
        return new TopicExchange(EXCHANGE_1);
    }

    @Bean
    public Binding binding(Queue queue1, TopicExchange exchange1){
        return BindingBuilder.bind(queue1).to(exchange1).with(ROUTING_KEY_1);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
