package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("list")
    public ModelAndView showList(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("wishlist");
        Page<Product> products = productService.findAll(pageable);
        modelAndView.addObject("");
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView showProductDetail(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("product-details");
        Optional<Product> product = productService.findById(id);
        modelAndView.addObject("product", product.get());
        return modelAndView;
    }
}
