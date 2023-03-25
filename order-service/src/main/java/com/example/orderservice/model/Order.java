package com.example.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "order")
@AllArgsConstructor
public class Order {
        @Id
        @GeneratedValue
        private Long id;
        private Long orderNumber;
        private Long userId;
        private Long productId;
        private Date createAt;
        private Boolean status;

}
