package com.example.demo.controller;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.model.AppUser;
import com.example.demo.model.Product;
import com.example.demo.service.appUser.AppUserService;
import com.example.demo.service.product.ProductService;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;
import java.util.Optional;


@Controller
@RequestMapping("/users")
public class UserController {
    String mCloudName = "dgaitn58c";
    String mApiKey = "295441413799595";
    String mApiSecret = "rUkaq_vJyNr4XnXEEZf4XweGgVU";

    @Autowired
    AppUserService appUserService;
    @Autowired
    ProductService productService;

    @GetMapping("")
    public ModelAndView index(@RequestParam("s") Optional<String> keyword, @RequestParam("page") Optional<Integer> page) {
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Product> products;
        int pageNum = 0;
        if (page.isPresent() && page.get() > 0) pageNum = page.get() - 1;
        Pageable pageRequest = PageRequest.of(pageNum, 9);
        if (keyword.isPresent()) {
            products = productService.findAllByNameContaining(keyword.get(), pageRequest);
            modelAndView.addObject("keyword", keyword.get());
        } else {
            products = productService.findAll(pageRequest);
        }
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("create")
    public String ShowCreate() {
        return "";
    }

    @PostMapping("/creat")
    public ModelAndView createUser(@ModelAttribute("") AppUser appUser, @ModelAttribute("AppUserImageFile") MultipartFile ImageFile) {
        ModelAndView modelAndView = new ModelAndView("");
//     appUser.setAppUser(appUserService.getCurrentUser());
        AppUser appUsers = appUserService.save(appUser);
        appUser.setAvatarFile(ImageFile);
        Cloudinary c = new Cloudinary("cloudinary://" + mApiKey + ":" + mApiSecret + "@" + mCloudName);
        try {
            File avFile = Files.createTempFile("temp", ImageFile.getOriginalFilename()).toFile();
            ImageFile.transferTo(avFile);
            Map responseAV = c.uploader().upload(avFile, ObjectUtils.emptyMap());
            JSONObject jsonAV = new JSONObject(responseAV);
            String urlAV = jsonAV.getString("url");
            appUser.setAvatar(urlAV);
            appUserService.save(appUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("", appUser);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<AppUser> appUser = appUserService.findById(id);
        ModelAndView modelAndView = new ModelAndView("");
        modelAndView.addObject("", appUser);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView eitUser(@ModelAttribute("") AppUser appUser, @ModelAttribute("AppUserImageFile") MultipartFile ImageFile) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
//       appUser.setAppUser(appUserService.getCurrentUser());
        AppUser appUsers = appUserService.save(appUser);
        appUser.setAvatarFile(ImageFile);
        Cloudinary c = new Cloudinary("cloudinary://" + mApiKey + ":" + mApiSecret + "@" + mCloudName);
        try {
            File avFile = Files.createTempFile("temp", ImageFile.getOriginalFilename()).toFile();
            ImageFile.transferTo(avFile);
            Map responseAV = c.uploader().upload(avFile, ObjectUtils.emptyMap());
            JSONObject jsonAV = new JSONObject(responseAV);
            String urlAV = jsonAV.getString("url");
            appUser.setAvatar(urlAV);
            appUserService.save(appUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("", appUser);
        return modelAndView;
    }
}