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

    @GetMapping("/createUser")
    public ModelAndView createUser() {
        return new ModelAndView("createUser", "appUser", new AppUser());
    }


    @PostMapping("/createUser")
    public String uploadFile(@ModelAttribute("appUser") AppUser myFile, @Param("avatarFile") MultipartFile multipartFile, Model model) {

        try {
            if (myFile.getRole() == null) {
                Role role = new Role();
                role.setId((long) 2);
                myFile.setRole(role);
            }
            multipartFile = myFile.getAvatarFile();
            String fileName = multipartFile.getOriginalFilename();
            AppUser myFile1 = new AppUser();
            myFile1.setAvatarFile(multipartFile);
            myFile1.setAvatar(fileName);
            myFile1.setOrderNumber(myFile.getOrderNumber());
            myFile1.setPassword(myFile.getPassword());
            myFile1.setUserName(myFile.getUserName());
//        myFile1.setAddress(myFile.getAddress());
            myFile1.setAddress(myFile.getAddress());
            myFile1.setRole(myFile.getRole());
            appUserService.save(myFile1);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Upload failed");
        }
        return "login";
    }

    @GetMapping("/detail/{id}")
    public ModelAndView showProductDetail(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("product-details");
        Optional<Product> product = productService.findById(id);
        modelAndView.addObject("product", product.get());
        return modelAndView;
    }
}