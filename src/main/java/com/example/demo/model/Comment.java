package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;
    private Date comment_date;
    private String content;
    private String noti;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"commentPost"})
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "appUser_id")
    private AppUser appUser;

    public void setNoti(String noti) {
        this.noti = appUser.getUserName() + "just commented on your post!";
    }
}
