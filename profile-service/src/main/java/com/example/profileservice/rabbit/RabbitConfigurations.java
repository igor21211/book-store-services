package com.example.profileservice.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitConfigurations {

    @Bean
    Exchange user() {
        return new TopicExchange("user", true, false);
    }

    @Bean
    Queue mailSenderToUser() {
        return new Queue("mail_sender_user", true, false, false);
    }

    @Bean
    Binding curtBinding(Queue mailSenderToUser, Exchange user){
        return new Binding(mailSenderToUser.getName(), Binding.DestinationType.QUEUE, user.getName(), "mail_sender_user", null);
    }
}
