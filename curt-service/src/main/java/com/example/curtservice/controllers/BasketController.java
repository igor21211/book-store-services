package com.example.curtservice.controllers;

import com.example.curtservice.model.Basket;
import com.example.curtservice.services.BasketService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "/curt", produces = MediaType.APPLICATION_JSON_VALUE)
public class BasketController {

    private BasketService service;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Basket createBasket(@RequestBody Basket basket){
        return service.create(basket);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Basket getBasketById(@PathVariable("id") Long id){
        return service.getBasketById(id);
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Basket> getListBasketByUserId(@PathVariable("id") Long id){
        return service.getBasketListByUserId(id);
    }

    @PutMapping("/{id}/addQuantity")
    @ResponseStatus(HttpStatus.OK)
    public Basket addQuantity(@PathVariable("id") Long id){
        return service.addQuantity(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Basket updateBasket(@PathVariable("id")Long id, Basket basket){
        return service.updateBasket(id,basket);
    }

    @PutMapping("/{id}/minusQuantity")
    @ResponseStatus(HttpStatus.OK)
    public Basket minusQuantity(@PathVariable("id") Long id){
        return service.minusQuantity(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBasket(@PathVariable("id") Long id){
        service.deleteBasketById(id);
    }
}
