package com.example.demo.service.cart;

import com.example.demo.model.AppUser;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;

import com.example.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Override
    public Iterable<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void remove(Long id) {
        cartRepository.deleteById(id);
    }


    @Override
    public Cart findByProductAndUser(Product product, AppUser user) {
        return cartRepository.findByProductAndAppUser(product,user);
    }

    @Override
    public Cart findAllByOrderNumberAndUser(Long orderNumber, AppUser user) {
        return cartRepository.getCartByOrderNumberAndAppUser(orderNumber,user);
    }

    @Override
    public Cart getCartByAppUser(AppUser user) {
        return cartRepository.getCartByAppUser(user);
    }


}
