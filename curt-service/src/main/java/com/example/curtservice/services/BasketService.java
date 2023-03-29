package com.example.curtservice.services;

import com.example.curtservice.model.Basket;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BasketService {
    Basket create(Basket basket) throws JsonProcessingException;

    Basket getBasketById(Long id);

    List<Basket> getBasketListByUserId(Long user_id);

    Basket setPriceToTheBasket(Long id);

    Basket updateBasket(Long id, Basket basket);

    Basket addQuantity(Long id);

    Basket minusQuantity(Long id);

    void deleteBasketById(Long id);


}
