package com.aiteam.springrabbitmqconsumer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String QUEUE = "message_queue";
    public static final String QUEUE_1 = "message_queue_1";
    public static final String EXCHANGE_1 = "message_exchange_1";
    public static final String ROUTING_KEY_1 = "message_routingKey_1";

    public static final String QUEUE_2 = "message_queue_2";
    public static final String EXCHANGE_2 = "message_exchange_2";

    public static final String ROUTING_KEY_2 = "message_routingKey__2";

    @Bean
    public Queue queue(){
      return new Queue(QUEUE);
    }

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

    @Bean
    public Queue queue2(){
        return new Queue(QUEUE_2);
    }

    @Bean
    public TopicExchange exchange2(){
        return new TopicExchange(EXCHANGE_2);
    }

    @Bean
    public Binding binding2(Queue queue2, TopicExchange exchange2){
        return BindingBuilder.bind(queue2).to(exchange2).with(ROUTING_KEY_2);
    }
}
