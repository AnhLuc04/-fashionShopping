package com.example.demo.service.appUser;


import com.example.demo.model.AppUser;
import com.example.demo.model.Role;
import com.example.demo.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public AppUser getUserByUsername(String username) {
        return appUserRepository.findByUserName(username);
    }

    @Override
    public Iterable<AppUser> getAllByRoleId(Long id) {
//        return appUserRepository.getAllByRoleId(id);
        return null;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = this.getUserByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(user.getRole());

        UserDetails userDetails = new User(
                user.getUserName(),
                user.getPassword(),
                authorities);
        return userDetails;
    }


    @Override
    public AppUser getCurrentUser() {
        AppUser user;
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        user = this.getUserByUsername(userName);
        return user;
    }

    @Override
    public Iterable<AppUser> getAllByRoleIsNotContaining(Long id) {
//        return appUserRepository.getAllByRoleIsNotContaining(id);
        return null;
    }

    @Override
    public Iterable<AppUser> getAllByRoleOrRole(Role role1, Role role2) {
//        return appUserRepository.getAllByRoleOrRole(role1, role2);
        return null;
    }

    @Override
    public Iterable<AppUser> getAllByNameIsContaining(String name) {
        return appUserRepository.getAllByUserNameContaining(name);
    }

    @Override
    public Iterable<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return appUserRepository.findById(id);
    }


    @Override
    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    public RedirectView remove(Long id) {
        appUserRepository.deleteById(id);
        return null;
    }


}
