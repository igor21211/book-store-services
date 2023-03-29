package com.example.mailnotificationservice.rabbit;

import com.example.mailnotificationservice.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class RabbitListeners {

    @SneakyThrows
    @RabbitListener(queues = "mail_sender_user")
    public void infoNewUsers(String message){
        User user = new ObjectMapper().readValue(message, User.class);
        //TODO add  logic to send message
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getEmail());
    }

}
