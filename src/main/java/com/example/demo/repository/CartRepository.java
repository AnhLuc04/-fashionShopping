package com.example.demo.repository;


import com.example.demo.model.AppUser;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    Cart findByProductAndAppUser(Product product, AppUser user);

    Cart getCartByOrderNumberAndAppUser(Long orderNumber, AppUser user);

    Cart getCartByAppUser( AppUser user);

}
