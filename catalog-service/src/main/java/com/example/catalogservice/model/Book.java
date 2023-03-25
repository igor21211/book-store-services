package com.example.catalogservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "book")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            name = "title",
            unique = true,
            nullable = false
    )
    private String title;
    @Column(
            name = "author",
            nullable = false
    )
    private String author;
    @Column(
            name = "price",
            nullable = false
    )
    private Double price;
    @Column(
            name = "quantity",
            nullable = false
    )
    private Integer quantity;
    @Column(name = "image")
    private String image;
}
