package com.example.demo.service.orderDetails;

import com.example.demo.model.OrderDetails;
import com.example.demo.service.GeneralService;

public interface OrderDetailsService extends GeneralService<OrderDetails> {
    OrderDetails findByOrderNumber(Long orderNumber);
}
