package com.example.demo.controller;


import com.example.demo.model.*;
import com.example.demo.service.appUser.AppUserService;
import com.example.demo.service.cart.CartService;
import com.example.demo.service.comment.CommentService;
import com.example.demo.service.orderDetails.OrderDetailsService;
import com.example.demo.service.product.ProductService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    AppUserService appUserService;
    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;
    @Autowired
    OrderDetailsService orderDetailsService;
    @Autowired
    CommentService commentService;

    @ModelAttribute("username")
    public String getPrincipal() {
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

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


    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {

        if (this.getUser().getUserId() == id) {
            ModelAndView modelAndView = new ModelAndView("editUser");
            Optional<AppUser> appUser = appUserService.findById(id);
            modelAndView.addObject("appUser", appUser);
            return modelAndView;
        } else {
            return new ModelAndView("noRight");
        }
    }

    @PostMapping("/edit")
    public ModelAndView eitUser(@ModelAttribute("appUser") AppUser myFile, @Param("avatarFile") MultipartFile multipartFile) {
        ModelAndView modelAndView = new ModelAndView("redirect:/login");


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
            myFile1.setRole(myFile.getRole());
            myFile1.setUserId(myFile.getUserId());
            appUserService.save(myFile1);
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.addObject("message", "Upload failed");
        }
        return modelAndView;
    }

    @GetMapping("/create/product")
    public ModelAndView createUser() {
        return new ModelAndView("createProduct", "product", new Product());
    }

    @PostMapping("/create/product")
    public ModelAndView createProduct(@ModelAttribute("product") Product myFile, @ModelAttribute("imgFile") MultipartFile multipartFile) {
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
//        myFile1.setProductId(myFile.getProductId());
        if (myFile.isStatus() == false) {
            myFile.setStatus(true);
        }
        myFile1.setStatus(myFile.isStatus());
        productService.save(myFile1);
        return modelAndView;
    }

    @GetMapping("/edit/product/{id}")
    public ModelAndView showEditProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("editProduct");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/edit/product")
    public ModelAndView editProduct(@ModelAttribute("product") Product myFile, @ModelAttribute("imgFile") MultipartFile multipartFile) {
        ModelAndView modelAndView = new ModelAndView("editProduct");
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
//        myFile1.setComment(myFile.getProductId());
//        if(myFile1.getComment()==null){
//            myFile.setComment("...");
//        }
        if (myFile.isStatus() == false) {
            myFile.setStatus(true);
        }
        myFile1.setStatus(myFile.isStatus());
        productService.save(myFile1);
        return modelAndView;

    }

    @GetMapping("delete/product/{id}")
    public RedirectView deleteProduct(@PathVariable long id) {
//        Optional<Product> product = productService.findById(id);
        productService.remove(id);
        return new RedirectView("");
    }

    @GetMapping("/OutProduct/{id}")
    public RedirectView outProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        Product product1 = product.get();
        product1.setStatus(false);
        productService.save(product1);
        return new RedirectView("");
    }

    @GetMapping("/OnProduct/{id}")
    public RedirectView onProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        Product product1 = product.get();
        product1.setStatus(true);
        productService.save(product1);
        return new RedirectView("");
    }

    @GetMapping("/createCart/{productId}")
    public RedirectView addCart(@PathVariable Long productId) {


        String principal = getPrincipal();
        AppUser user = appUserService.getUserByUsername(principal);
        Cart cart = cartService.findAllByOrderNumberAndUser(user.getOrderNumber(), user);

        Long productID = cart.getProduct().getProductId();
        if (productID == productId) {
            Product product = productService.findByProductId(productId);
            Cart cart1 = new Cart(user, product, (long) +1, this.getUser().getOrderNumber());
        } else {
            return new RedirectView("noRight");
        }
        return new RedirectView("");
    }

    private AppUser getUser() {
        String name = this.getPrincipal();
        if (name.contains("anonymousUser")) {
            return null;
        } else {
            return appUserService.getUserByUsername(name);
        }
    }

    @GetMapping("/buy")
    public String buy() {
        String principal = getPrincipal();
        AppUser user = appUserService.getUserByUsername(principal);
        Long sum = Long.valueOf(0);
        Cart carts = cartService.getCartByAppUser( user);
        Product product = productService.findByProductId(carts.getProduct().getProductId());
        product.setQuantity(product.getQuantity() - carts.getQuantity());
        productService.save(product);
        sum += carts.getQuantity() * carts.getProduct().getPrice();

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderNumber(this.getUser().getOrderNumber() + 1);
        orderDetails.setTotalPrice(sum);
        orderDetailsService.save(orderDetails);
        this.getUser().setOrderNumber(this.getUser().getOrderNumber());
        appUserService.save(this.getUser());
        return "redirect:/";
    }
    @PostMapping("/creat/comment")
    public ModelAndView homeComment( @ModelAttribute("content") String content){
        ModelAndView modelAndView = new ModelAndView("redirect:/users");

//        Product product1 = productService.findById(id).get();
        String principal = getPrincipal();
        AppUser user = appUserService.getUserByUsername(principal);
        Cart carts = cartService.getCartByAppUser( user);
        Product product1= productService.findByProductId(carts.getProduct().getProductId())
        Comment commentPost = new Comment();//tạo comment lưu và database. rồi mới xét lại commment cho post
        commentPost.setContent(content);
        commentPost.setAppUser(appUserService.getCurrentUser());
        commentPost.setProduct(product1);
       commentService.save(commentPost);

        product1.setComment((List<Comment>) commentService.getAllByProduct(product1));
        productService.save(product1);

        return modelAndView;
    }
}