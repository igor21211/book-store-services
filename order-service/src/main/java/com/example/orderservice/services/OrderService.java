package com.example.orderservice.services;

import com.example.orderservice.model.Order;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface OrderService {
    Order create(Order order);

    Order getOrder(Long id);

    Page<Order> getAllOrders(Long userId,
                             Long productId,
                             String productName,
                             Integer quantity,
                             Double priceSum,
                             Date createAt,
                             Boolean status,
                             int page,
                             int size,
                             List<String> sortList,
                             String sortOrder);

    Order updateOrder(Long id, Order order);
    void delete(Long id);
}
