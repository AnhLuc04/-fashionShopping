package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Product;
import com.example.demo.model.Role;
import com.example.demo.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;
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

//    @GetMapping("create")
//    public String showCreate() {
//        return "";
//    }
//
//    @PostMapping("/create/product")
//    public ModelAndView createProduct(@ModelAttribute("Product") Product myFile, @ModelAttribute("img") MultipartFile multipartFile) {
//        ModelAndView modelAndView = new ModelAndView("");
////        try {
////            if(myFile.getRole()==null){
////                Role role= new Role();
////                role.setId((long) 2);
////                myFile.setRole(role);
////            }
//        multipartFile = myFile.getImgFile();
//        String fileName = multipartFile.getOriginalFilename();
//        Product myFile1 = new Product();
//        myFile1.setImgFile(multipartFile);
//        myFile1.setImg(fileName);
//        myFile1.setName(myFile.getName());
//        myFile1.setPrice(myFile.getPrice());
//        myFile1.setQuantity(myFile.getQuantity());
////        myFile1.setProductId(myFile.getProductId());
//        if (myFile.isStatus() == false) {
//            myFile.setStatus(true);
//        }
//        myFile1.setStatus(myFile.isStatus());
//        productService.save(myFile1);
//        return modelAndView;
//    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("");
        modelAndView.addObject("", product);
        return modelAndView;
    }

    @PostMapping("edit")
    public ModelAndView editProduct(@ModelAttribute("Product") Product myFile, @ModelAttribute("img") MultipartFile multipartFile) {
        ModelAndView modelAndView = new ModelAndView("createProduct");
//        try {
//            if(myFile.getRole()==null){
//                Role role= new Role();
//                role.setId((long) 2);
//                myFile.setRole(role);
//            }
        multipartFile = myFile.getImgFile();
        String fileName = multipartFile.getOriginalFilename();
        Product myFile1 = new Product();
        myFile1.setImgFile(multipartFile);
        myFile1.setImg(fileName);
        myFile1.setName(myFile.getName());
        myFile1.setPrice(myFile.getPrice());
        myFile1.setQuantity(myFile.getQuantity());
        myFile1.setProductId(myFile.getProductId());
        if (myFile.isStatus() == false) {
            myFile.setStatus(true);
        }
        myFile1.setStatus(myFile.isStatus());
        productService.save(myFile1);
        return modelAndView;

    }



}
