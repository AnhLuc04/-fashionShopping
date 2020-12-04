package com.example.demo.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.model.Product;
import com.example.demo.service.product.ProductService;
import org.cloudinary.json.JSONObject;
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
    String mCloudName = "dgaitn58c";
    String mApiKey = "295441413799595";
    String mApiSecret = "rUkaq_vJyNr4XnXEEZf4XweGgVU";
    @Autowired
    ProductService productService;

    @GetMapping("list")
    public ModelAndView showList(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("");
        Page<Product> products = productService.findAll(pageable);
        modelAndView.addObject("");
        return modelAndView;
    }

    @GetMapping("create")
    public String showCreate() {
        return "";
    }

    @PostMapping("create")
    public ModelAndView createProduct(@ModelAttribute("Product") Product product, @ModelAttribute("ImageFile") MultipartFile ImageFile) {


        ModelAndView modelAndView = new ModelAndView("");
        Product products = productService.save(product);
        products.setImgFile(ImageFile);
        Cloudinary c = new Cloudinary("cloudinary://" + mApiKey + ":" + mApiSecret + "@" + mCloudName);
        try {
            File avFile = Files.createTempFile("temp", ImageFile.getOriginalFilename()).toFile();
            ImageFile.transferTo(avFile);
            Map responseAV = c.uploader().upload(avFile, ObjectUtils.emptyMap());
            JSONObject jsonAV = new JSONObject(responseAV);
            String urlAV = jsonAV.getString("url");
            products.setImg(urlAV);
            productService.save(products);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("", products);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("");
        modelAndView.addObject("", product);
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public RedirectView deleteProduct(@PathVariable long id) {
        Optional<Product> product = productService.findById(id);
        return new RedirectView("");
    }

}
