package com.example.demo.controller;


import com.example.demo.model.AppUser;
import com.example.demo.model.Product;
import com.example.demo.model.Role;
import com.example.demo.service.appUser.AppUserService;
import com.example.demo.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
public class HomeController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ProductService productService;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login", "appUser", new AppUser());
    }

    @GetMapping("/register")
    public ModelAndView showRegisterForm() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("appUser", new AppUser());
        return modelAndView;
    }

    @GetMapping("/khongcoquyen")
    public String accessDenied() {
        return "noRight";
    }
}