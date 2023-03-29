package com.example.orderservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "orders")
public class Order {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(
                name = "user_id"
        )
        private Long userId;
        @Column(
                name = "product_id"
        )
        private Long productId;

        @Column(
                name = "quantity"
        )
        private Integer quantity;
        @Column(
                name = "price"
        )
        private Double price;
        @Column(
                name = "create_at"
        )
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private Date createAt = new Date();
        @Column(name = "status")
        private Boolean status = false;

}
