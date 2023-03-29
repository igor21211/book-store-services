package com.example.orderservice.services;

import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import com.fasterxml.jackson.databind.node.DoubleNode;
import jakarta.persistence.criteria.Expression;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public Order create(Order order) {
        return repository.save(order);
    }

    @Override
    public Order getOrder(Long id) {
        return repository.findById(id).orElseThrow(()-> new IllegalArgumentException("Order not found with this id "+id));
    }

    @Override
    public Page<Order> getAllOrders(Long userId,
                                    Long productId,
                                    String productName,
                                    Integer quantity,
                                    Double priceSum,
                                    Date createAt,
                                    Boolean status,
                                    int page,
                                    int size,
                                    List<String> sortList,
                                    String sortOrder) {
        Pageable pageable = PageRequest.of(page,size,Sort.by(createSortOrder(sortList,sortOrder)));
        return repository.findAll(getSpecification(userId, productId, productName, quantity, priceSum, createAt, status),pageable);
    }

    private Specification<Order> getSpecification(final Long userId,
                                                  final Long productId,
                                                  final String productName,
                                                  final Integer quantity,
                                                  final Double priceSum,
                                                  final Date createAt,
                                                  final Boolean status
                                                  ){
        return (((root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.and();
            if(userId!=null) predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("userId"), userId));
            if(productId!=null) predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("productId"), productId));
            if(productName!=null) predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("productName"), "%"+productName+"%"));
            if(quantity!= null) predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("quantity"),quantity));
            if(priceSum!=null) predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("priceSum"), priceSum));
            if(createAt!=null) predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("createAt"), createAt));
            if(status!=null) predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), status));
            return predicate;
        }));
    }

    private List<Sort.Order> createSortOrder(List<String> sortList, String sortDirection) {
        List<Sort.Order> sorts = new ArrayList<>();
        Sort.Direction direction;
        for (String sort : sortList) {
            if (sortDirection != null) {
                direction = Sort.Direction.fromString(sortDirection);
            } else {
                direction = Sort.Direction.DESC;
            }
            sorts.add(new Sort.Order(direction, sort));
        }
        return sorts;
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        return repository.findById(id).map(
                orderNew -> {
                    orderNew.setUserId(order.getUserId());
                    orderNew.setProductId(order.getProductId());
                    orderNew.setQuantity(order.getQuantity());
                    orderNew.setCreateAt(new Date());
                    orderNew.setStatus(false);
                    return  repository.save(orderNew);
                }
        ).orElseThrow(()-> new IllegalArgumentException("Order not found with this id "+id));
    }

    @Override
    public void delete(Long id) {
         repository.deleteById(id);
    }
}
