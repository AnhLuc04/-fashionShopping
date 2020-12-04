package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Role;
import com.example.demo.service.appUser.AppUserService;
import com.example.demo.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.naming.Name;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    RoleService roleService;
    @Autowired
    AppUserService appUserService;

    @GetMapping("list")
    public ModelAndView showList(Pageable pageInfo) {
        ModelAndView modelAndView = new ModelAndView("");
        Role role_user = roleService.getById((long) 1);
        Role role_guest = roleService.getById((long) 2);
        Iterable<AppUser> citys = appUserService.getAllByRoleOrRole(role_user,role_guest);
        modelAndView.addObject("city", citys);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public RedirectView deleteUser(@PathVariable Long id) {
        Optional<AppUser> User = (Optional<AppUser>) appUserService.findById(id);
        if (User.get().getRole().getName().equals("ROLE_USER")) {
            return appUserService.remove(id);
        } else if (User.get().getRole().getName().equals("ROLE_ADMIN") ){
            return new RedirectView("");
        }
        return new RedirectView("");
    }
}
