package com.example.curtservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "basket")
@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            name = "user_id",
            nullable = false
    )
    private Long userId;
    @Column(
            name = "product_id",
            nullable = false
    )
    private Long productId;
    @Column(
            name = "quantity",
            nullable = false
    )
    private Integer quantity;
    @Column(name = "price")
    private Double price;
    @Column(
            name = "status"
    )
    private Boolean status = false;
}
