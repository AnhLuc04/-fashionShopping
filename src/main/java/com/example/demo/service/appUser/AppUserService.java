package com.example.demo.service.appUser;

import com.example.demo.model.AppUser;
import com.example.demo.model.Role;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Service
@Component
public interface AppUserService {
    AppUser getUserByUsername(String username);
//    AppUser findByName(String name);
    Iterable<AppUser> getAllByRoleId(Long id);

    AppUser getCurrentUser();

    Iterable<AppUser> getAllByRoleIsNotContaining(Long id);

    Iterable<AppUser> getAllByNameIsContaining(String name);

    Iterable<AppUser> getAllByRoleOrRole(Role role1, Role role2);

    Iterable<AppUser> findAll();

    Optional<AppUser> findById(Long id);

    AppUser save(AppUser appUser);

    RedirectView remove(Long id);
}
