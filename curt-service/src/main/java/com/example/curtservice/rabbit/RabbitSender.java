package com.example.curtservice.rabbit;


import com.example.curtservice.model.Basket;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendCurtInfoEvent(String message){
        rabbitTemplate.convertAndSend("order", "addOrder", message);
    }
}
