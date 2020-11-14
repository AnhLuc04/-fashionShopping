package com.example.demo;

import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceIml;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public ProductService productService() {
        return new ProductServiceIml();
    }
}
