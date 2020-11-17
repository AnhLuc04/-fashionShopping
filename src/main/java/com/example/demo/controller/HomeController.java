package com.example.demo.controller;



import com.example.demo.model.AppUser;
import com.example.demo.service.appUser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private AppUserService appUserService;

//    @Autowired
//    private RoleService roleService;

    @GetMapping("khongcoquyen")
    public String accessDenied(){
        return "noRight";
    }

    @GetMapping("/")
    public ModelAndView login1(){
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public ModelAndView showRegisterForm(){
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("appUser",new AppUser());
        return modelAndView;
    }

}
