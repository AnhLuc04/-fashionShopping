package com.example.demo.repository;


import com.example.demo.model.AppUser;

import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser,Long> {

    AppUser findByUserName(String username);

//    Iterable<AppUser> getAllByRoleId(Long id);
//
//    Iterable<AppUser> getAllByRoleIsNotContaining(Long id);
//
//    Iterable<AppUser> getAllByRoleOrRole(Role role1, Role role2);

//    Iterable<AppUser> getAllByUserNameIsContaining(String name);
    Iterable<AppUser> getAllByUserNameContaining(String name);
}
