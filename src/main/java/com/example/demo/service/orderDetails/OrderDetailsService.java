package com.example.demo.service.orderDetails;

import com.example.demo.model.Order;
import com.example.demo.service.GeneralService;

public interface OrderDetailsService extends GeneralService<Order> {
    Order findByOrderNumber(Long orderNumber);
}
