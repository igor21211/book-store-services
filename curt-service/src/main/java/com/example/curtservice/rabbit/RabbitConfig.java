package com.example.curtservice.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class RabbitConfig {
//
//    @Bean
//    Exchange curtBook() {
//        return new TopicExchange("order", true, false);
//    }
//
//    @Bean
//    Queue orderFromCurt() {
//        return new Queue("addOrder", true, false, false);
//    }
//
//    @Bean
//    Binding curtBinding(Queue orderFromCurt, Exchange curtBook){
//        return new Binding(orderFromCurt.getName(), Binding.DestinationType.QUEUE, curtBook.getName(), "addOrder", null);
//    }
//}
