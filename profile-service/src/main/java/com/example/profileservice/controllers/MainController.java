package com.example.profileservice.controllers;

import com.example.profileservice.model.User;
import com.example.profileservice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController {

    private UserService service;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user){
        User userNew =  service.create(user);
        service.setDataCreatedByUser(userNew.getId());
        return userNew;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getById(@PathVariable("id") Long id){
        return service.getUserById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUserById(@PathVariable("id") Long id, @RequestBody User user){
        return service.updateUserById(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus
    public void deleteUserById(@PathVariable("id") Long id){
        service.deleteUserById(id);
    }
}
