package com.example.profileservice.rabbit;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitSender {

    @Autowired
    private  RabbitTemplate rabbitTemplate;

    public void sendInfoUser(String message){
        rabbitTemplate.convertAndSend("user", "mail_sender_user", message);
    }
}
