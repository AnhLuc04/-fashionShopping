package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Role;
import com.example.demo.service.appUser.AppUserService;
import com.example.demo.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    RoleService roleService;
    @Autowired
    AppUserService appUserService;

    @GetMapping("list")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("");
        Role role_user = roleService.getById((long) 1);
        Role role_guest = roleService.getById((long) 2);
        Iterable<AppUser> citys = appUserService.getAllByRoleOrRole(role_user,role_guest);
        modelAndView.addObject("city", citys);
        return modelAndView;
    }


    @GetMapping("/create/admin/{id}")
    public   RedirectView createAdmin(@PathVariable Long id){
        Optional<AppUser> User =appUserService.findById(id);
        AppUser User1 = User.get();
        Role role = roleService.getById((long)1);
        User1.setRole(role);
        appUserService.save(User1);
        return  new RedirectView("");
    }
    @GetMapping("/create/user/{id}")
    public   RedirectView createUser(@PathVariable Long id){
        Optional<AppUser> User =appUserService.findById(id);
        AppUser User1 = User.get();
        Role role = roleService.getById((long)2);
        User1.setRole(role);
        appUserService.save(User1);
        return  new RedirectView("");
    }
    @GetMapping("/unBlockUser/{id}")
    public RedirectView unBlockUser(@PathVariable Long id){
        Optional<AppUser> User =appUserService.findById(id);
        AppUser User1 = User.get();
        Role role = roleService.getById((long)3);
        User1.setRole(role);
        appUserService.save(User1);
        return  new RedirectView("");
    }
    @GetMapping("/delete/{id}")
    public  RedirectView deleteAdmin(@PathVariable Long id){
        Optional<AppUser> User =appUserService.findById(id);
        AppUser User1 = User.get();
        Role role = roleService.getById((long)2);
        User1.setRole(role);
        appUserService.save(User1);
        return  new RedirectView("");
    }
    @GetMapping("/{id}")
    public RedirectView deleteUser(@PathVariable Long id) {
        Optional<AppUser> User = appUserService.findById(id);
        AppUser User1 = User.get();
        Role role = roleService.getById((long) 3);
        User1.setRole(role);
        appUserService.save(User1);
        return new RedirectView("");
    }
}
