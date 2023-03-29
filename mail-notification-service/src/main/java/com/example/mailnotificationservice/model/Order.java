package com.example.mailnotificationservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import java.util.Date;
@Data
public class Order {

    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private Double price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date createAt;

}
