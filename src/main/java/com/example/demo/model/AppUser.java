package com.example.demo.model;


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;
    private Long orderNumber;
    @ManyToOne
    public Role role;
//    @OneToMany(mappedBy = "user")
//    private Set<Cart> carts;

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



    public AppUser(Long userId, String userName, String password, String avatar, String address, Long orderNumber, Role role, Set<Cart> carts) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.avatar = avatar;
        this.address = address;
        this.orderNumber = orderNumber;
        this.role = role;
//        this.carts = carts;
    }

    public AppUser(String name, String password, String avatar, String address, Long orderNumber, Role role, Set<Cart> carts) {
        this.userName = name;
        this.password = password;
        this.avatar = avatar;
        this.address = address;
        this.orderNumber = orderNumber;
        this.role = role;
//        this.carts = carts;
    }
}
