package com.insectbyte.iprwc.daos;

import com.insectbyte.iprwc.models.Order;
import com.insectbyte.iprwc.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class OrderDAO {

    private final OrderRepository ORDER_REPOSITORY;

    @Autowired
    public OrderDAO(OrderRepository orderRepository) {
        this.ORDER_REPOSITORY = orderRepository;
    }

    public void createOrders(ArrayList<Order> orders) {
        this.ORDER_REPOSITORY.saveAll(orders);
    }

    public ArrayList<Order> getOrdersFromUserId(UUID id) {
        return this.ORDER_REPOSITORY.getByUserId(id);
    }

    public ArrayList<Order> getOrders() {
        return new ArrayList<>(this.ORDER_REPOSITORY.findAll());
    }
}
