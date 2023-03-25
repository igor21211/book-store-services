package com.example.curtservice.services;

import com.example.curtservice.model.Basket;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BasketService {
    Basket create(Basket basket);

    Basket getBasketById(Long id);

    List<Basket> getBasketListByUserId(Long user_id);

    Basket setPriceToTheBasket(Long id);

    Basket updateBasket(Long id, Basket basket);

    Basket addQuantity(Long id);

    Basket minusQuantity(Long id);

    void deleteBasketById(Long id);


}
