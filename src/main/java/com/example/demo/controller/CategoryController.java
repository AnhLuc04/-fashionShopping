package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Cart;
import com.example.demo.model.Category;
import com.example.demo.model.jwt.JwtResponse;
import com.example.demo.service.appUser.AppUserService;
import com.example.demo.service.cart.CartService;
import com.example.demo.service.category.CategoryService;
import com.example.demo.service.jwt.JwtService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@CrossOrigin("*")
@Controller
@RequestMapping
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    //Lấy tất cả
    @GetMapping("/category")
    public ResponseEntity<List<Category>> showSanPham() {
        List<Category> categories = (List<Category>) categoryService.findAll();
        if (categories == null) {
            return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    //Lấy ở dữ liệu maenu ở tần thứ hai cần truyền tham số vào
    @GetMapping("/category/{parentID}")
    public ResponseEntity<List<Category>> showCategoryParentID(@PathVariable("parentID") long parentID) {
        List<Category> categories = (List<Category>) categoryService.findAllCategoryByParentID(parentID);
        if (categories == null) {
            return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    //Lấy theo id
    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> getCustomer(@PathVariable("id") long id) throws NotFoundException {
        Category category = categoryService.findById(id);
        if (category == null) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    // lưu sản phẩm vào DB
    @PostMapping(value = "/saveCategory")
    public ResponseEntity<Void> createProduct(@RequestBody Category category, UriComponentsBuilder ucBuilder) {
        categoryService.save(category);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/category/{id}").buildAndExpand(category.getCategoryId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Update
    @RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Category> updateCustomer(@PathVariable("id") long id, @RequestBody Category category) throws NotFoundException {
        Category currentCustomer = categoryService.findById(id);

        if (currentCustomer == null) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
        categoryService.save(category);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    //Xóa theo id
    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AppUser> deleteCustomer(@PathVariable("id") long id) throws NotFoundException {
        Category gioHang = categoryService.findById(id);
        if (gioHang == null) {
            return new ResponseEntity<AppUser>(HttpStatus.NOT_FOUND);
        }
        categoryService.remove(id);
        return new ResponseEntity<AppUser>(HttpStatus.NO_CONTENT);
    }

}
