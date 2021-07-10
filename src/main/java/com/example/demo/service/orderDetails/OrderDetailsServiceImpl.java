package com.example.demo.service.orderDetails;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Override
    public Iterable<Order> findAll() {
        return null;
    }

    @Override
    public Order findById(Long id) {
        return orderDetailsRepository.getOne(id);
    }

    @Override
    public Order save(Order order) {
        return orderDetailsRepository.save(order);
    }

    @Override
    public void remove(Long id) {
        orderDetailsRepository.deleteById(id);
    }

    @Override
    public Order findByOrderNumber(Long orderNumber) {
        return orderDetailsRepository.findByOrderNumber(orderNumber);
    }
}
