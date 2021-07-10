package com.example.demo.controller;

import com.example.demo.model.jwt.JwtResponse;
import com.example.demo.service.jwt.JwtService;
import com.example.demo.model.AppUser;
import com.example.demo.model.Cart;
import com.example.demo.model.Role;
import com.example.demo.service.appUser.AppUserService;
import com.example.demo.service.role.RoleService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
@CrossOrigin("*")
@Controller
@RequestMapping
public class AdminController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AppUserService nguoiDungService;
    @Autowired
    private JwtService jwtService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AppUser user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateAccessToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    //Lấy tất cả
    @GetMapping("/profiles")
    public ResponseEntity<List<AppUser>> showSanPham() {
        List<AppUser> carts = (List<AppUser>) nguoiDungService.findAll();
        if (carts == null) {
            return new ResponseEntity<List<AppUser>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<AppUser>>(carts, HttpStatus.OK);
    }

    //Lấy theo id
    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUser> getCustomer(@PathVariable("id") long id) throws NotFoundException {
        AppUser cart = nguoiDungService.findById(id);
        if (cart == null) {
            return new ResponseEntity<AppUser>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<AppUser>(cart, HttpStatus.OK);
    }

    // lưu sản phẩm vào DB
    @PostMapping(value = "/saveProfile")
    public ResponseEntity<Void> createProduct(@RequestBody AppUser nguoiDung, UriComponentsBuilder ucBuilder) {
        nguoiDungService.save(nguoiDung);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/profile/{id}").buildAndExpand(nguoiDung.getUserId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Update
    @RequestMapping(value = "/profile/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AppUser> updateCustomer(@PathVariable("id") long id, @RequestBody AppUser appUser) throws NotFoundException {
        AppUser currentCustomer = nguoiDungService.findById(id);

        if (currentCustomer == null) {
            return new ResponseEntity<AppUser>(HttpStatus.NOT_FOUND);
        }
        nguoiDungService.save(appUser);
        return new ResponseEntity<AppUser>(appUser, HttpStatus.OK);
    }

    //Xóa theo id
    @RequestMapping(value = "/profile/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AppUser> deleteCustomer(@PathVariable("id") long id) throws NotFoundException {
        AppUser gioHang = nguoiDungService.findById(id);
        if (gioHang == null) {
            return new ResponseEntity<AppUser>(HttpStatus.NOT_FOUND);
        }
        nguoiDungService.remove(id);
        return new ResponseEntity<AppUser>(HttpStatus.NO_CONTENT);
    }
}
