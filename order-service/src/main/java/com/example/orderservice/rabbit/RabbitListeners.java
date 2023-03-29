package com.example.orderservice.rabbit;

import com.example.orderservice.model.Order;
import com.example.orderservice.services.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@AllArgsConstructor
public class RabbitListeners {

    private final OrderService service;
    @SneakyThrows
    @RabbitListener(queues = "addOrder")
    public void onOrderListener(String message){
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.readValue(message,Order.class);
        service.create(order);
    }
}
