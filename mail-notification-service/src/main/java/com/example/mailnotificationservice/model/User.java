package com.example.mailnotificationservice.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date createAt;

    private Boolean status;
}
