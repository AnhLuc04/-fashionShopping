package com.example.demo.service.orderDetails;

import com.example.demo.model.OrderDetails;
import com.example.demo.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OrderDetailsServiceImpl implements  OrderDetailsService {
    @Autowired
    OrderDetailsRepository orderDetailsRepository;
    @Override
    public Iterable<OrderDetails> findAll() {
        return null;
    }

    @Override
    public Optional<OrderDetails> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public OrderDetails save(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public OrderDetails findByOrderNumber(Long orderNumber) {
        return orderDetailsRepository.findByOrderNumber(orderNumber);
    }
}
