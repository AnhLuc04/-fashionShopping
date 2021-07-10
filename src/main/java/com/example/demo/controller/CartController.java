package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.service.cart.CartService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService gioHangService;
    //Lấy tất cả
    @GetMapping("/carts")
    public ResponseEntity<List<Cart>> showCart() {
        List<Cart> carts = (List<Cart>) gioHangService.findAll();
        if (carts == null) {
            return new ResponseEntity<List<Cart>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Cart>>(carts, HttpStatus.OK);
    }
    //Lấy theo id
    @RequestMapping(value = "/cart/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cart> getCart(@PathVariable("id") long id) throws NotFoundException {
        Cart cart = gioHangService.findById(id);
        if (cart == null) {
            return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }
    // lưu sản phẩm vào DB
    @PostMapping(value = "/saveCart")
    public ResponseEntity<Void> createCart(@RequestBody Cart cart, UriComponentsBuilder ucBuilder) {
        gioHangService.save(cart);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/cart/{id}").buildAndExpand(cart.getCartId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    //Update
    @RequestMapping(value = "/cart/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Cart> updateCart(@PathVariable("id") long id, @RequestBody Cart cart) throws NotFoundException {
        Cart currentCustomer = gioHangService.findById(id);

        if (currentCustomer == null) {
            return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
        }
        gioHangService.save(cart);
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }
    //Xóa theo id
    @RequestMapping(value = "/cart/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Cart> deleteCart(@PathVariable("id") long id) throws NotFoundException {
        Cart cart = gioHangService.findById(id);
        if (cart == null) {
            return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
        }
        gioHangService.remove(id);
        return new ResponseEntity<Cart>(HttpStatus.NO_CONTENT);
    }
}
