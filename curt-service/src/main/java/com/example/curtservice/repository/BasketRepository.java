package com.example.curtservice.repository;

import com.example.curtservice.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long>, JpaSpecificationExecutor<Basket> {

    List<Basket> findAllByUserId(Long id);
}
