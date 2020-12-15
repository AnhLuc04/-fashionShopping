package com.example.demo.service.cart;

import com.example.demo.model.AppUser;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.service.GeneralService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public interface CartService extends GeneralService<Cart> {
    Cart findByProductAndUser(Product product, AppUser user);
    Cart findAllByOrderNumberAndUser(Long orderNumber, AppUser user);
    Cart getCartByAppUser( AppUser user);
}
