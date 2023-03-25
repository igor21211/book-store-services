package com.example.curtservice.services;

import com.example.curtservice.model.Basket;
import com.example.curtservice.repository.BasketRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final String URL_CATALOG = "lb://CATALOG-SERVICE/book/{id}";
    private final String URL_PROFILE = "lb://PROFILE-SERVICE/user/{id}";
     private RestTemplate restTemplate;
     private BasketRepository repository;


    @Override
    public Basket create(Basket basket) {
        if(!basket.getUserId().equals(checkProductAndUser(basket.getUserId(),URL_PROFILE))){
            throw new IllegalArgumentException("User id is not available");
        }
        if(!basket.getProductId().equals(checkProductAndUser(basket.getProductId(),URL_CATALOG))){
            throw new IllegalArgumentException("Product is not available");
        }
        if(checkActiveUser(basket.getUserId())){
            throw new IllegalArgumentException("User doesnt active");
        }
        Basket basketNew =  repository.save(basket);
        return setPriceToTheBasket(basketNew.getProductId());
    }
    public Basket setPriceToTheBasket(Long id){
        return repository.findById(id).map(
                basket -> {
                    basket.setPrice(getPriceProduct(basket.getProductId()));
                    return repository.save(basket);
                }
        ).orElseThrow(()-> new RuntimeException("Product cant find with this id: " + id));
    }

    public Long checkProductAndUser(Long product_id, String url){
        ResponseEntity<Basket> responseEntity = restTemplate.getForEntity(
                url,
                Basket.class,
                Map.of("id", product_id)
        );
        return responseEntity.getBody().getId();
    }


    public Boolean checkActiveUser(Long user_id) {
        ResponseEntity<Basket> responseEntity = restTemplate.getForEntity(
                URL_PROFILE,
                Basket.class,
                Map.of("id", user_id)
        );
        return responseEntity.getBody().getStatus();
    }

    public Double getPriceProduct(Long product_id){
        ResponseEntity<Basket> responseEntity = restTemplate.getForEntity(
                URL_CATALOG,
                Basket.class,
                Map.of("id", product_id)
        );
        return responseEntity.getBody().getPrice();
    }

    @Override
    public Basket getBasketById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Can't find basket with this id: " + id)) ;
    }

    @Override
    public List<Basket> getBasketListByUserId(Long user_id) {
        return repository.findAllByUserId(user_id);
    }

    @Override
    public Basket updateBasket(Long id, Basket basket) {
        return repository.findById(id).map(
                basket1 -> {
                    basket1.setQuantity(basket.getQuantity());
                    basket1.setProductId(basket.getProductId());
                    return repository.save(basket1);
                }
        ).orElseThrow(()-> new RuntimeException("Cant find basket with this id: " + id));
    }

    @Override
    public Basket addQuantity(Long id) {
    Integer basketOlQuantity = repository.findById(id).get().getQuantity();
        return repository.findById(id).map(
                basket -> {
                    basket.setQuantity(basketOlQuantity+1);
                    return  repository.save(basket);
                }
        ).orElseThrow(()-> new RuntimeException("Cant find basket with this id: " + id));

    }

    @Override
    public Basket minusQuantity(Long id) {
        Integer basketOlQuantity = repository.findById(id).get().getQuantity();
        return repository.findById(id).map(
                basket -> {
                    basket.setQuantity(basketOlQuantity-1);
                    return  repository.save(basket);
                }
        ).orElseThrow(()-> new RuntimeException("Cant find basket with this id: " + id));
    }

    @Override
    public void deleteBasketById(Long id) {
        repository.deleteById(id);
    }
}
