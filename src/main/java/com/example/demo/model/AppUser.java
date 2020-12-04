package com.example.demo.model;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Set;


@Entity

public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String password;
    private String avatar;
    private String address;
    private boolean status;
    private Long orderNumber;

    @Transient
    private MultipartFile avatarFile;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @ManyToOne
    public Role role;

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public AppUser() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public MultipartFile getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(MultipartFile avatarFile) {
        this.avatarFile = avatarFile;
    }

//    public Set<Cart> getCarts() {
//        return carts;
//    }
//
//    public void setCarts(Set<Cart> carts) {
//        this.carts = carts;
//    }

    public AppUser(Long userId, String userName, String password, String avatar, String address, boolean status, Long orderNumber, MultipartFile avatarFile, Role role, Set<Cart> carts) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.avatar = avatar;
        this.address = address;
//        this.status = status;
        this.orderNumber = orderNumber;
        this.avatarFile = avatarFile;
        this.role = role;
//        this.carts = carts;
    }

    public AppUser(String userName, String password, String avatar, String address, boolean status, Long orderNumber, MultipartFile avatarFile, Role role, Set<Cart> carts) {
        this.userName = userName;
        this.password = password;
        this.avatar = avatar;
        this.address = address;
//        this.status = status;
        this.orderNumber = orderNumber;
        this.avatarFile = avatarFile;
        this.role = role;
//        this.carts = carts;
    }
}
