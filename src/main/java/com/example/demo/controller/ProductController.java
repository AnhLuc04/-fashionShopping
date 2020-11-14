package com.example.demo.controller;


import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/list")
    public ModelAndView showList(@RequestParam("s") Optional<String> keyword, @RequestParam("page") Optional<Integer> page) {
        ModelAndView modelAndView = new ModelAndView("/index");
        Page<Product> products;
        int pageNum = 0;
        if (page.isPresent() && page.get() > 0) pageNum = page.get() - 1;
        Pageable pageRequest = PageRequest.of(pageNum, 10);
        if (keyword.isPresent()) {
            products = productService.findAllByNameContaining(keyword.get(), pageRequest);
            modelAndView.addObject("keyword", keyword.get());
        } else {
            products = productService.findAll(pageRequest);
        }
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
