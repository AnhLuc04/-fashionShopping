package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
//@Component("repository")
public class DemoApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @Autowired
    Environment evn;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String fileUpload = evn.getProperty("file_upload").toString();

        // Image resource9.
        registry.addResourceHandler("/i/**") //
                .addResourceLocations("file:" + fileUpload);
    }
}
