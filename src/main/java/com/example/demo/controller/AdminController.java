package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller("/admin")
public class AdminController {
    @ModelAttribute("")
    public String Cart(){
        return "cart";
    }
}
