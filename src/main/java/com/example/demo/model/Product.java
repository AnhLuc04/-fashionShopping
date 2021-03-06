package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private Long price;
    private Long quantity;
    private String size;
    private String description;
    private String img;
    private boolean status = false;
    @Transient
    private MultipartFile imgFile;
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, targetEntity = Comment.class)
    @JsonIgnoreProperties(value = {"product"})
    Set<Comment> comment;
//    @OneToMany(mappedBy = "product")
//    private Set<Cart> carts;
//
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    public Product() {
    }

//    public Product( String product_Name, Long price, Long quantity, String img, MultipartFile imgFile) {
//        Product_Name = product_Name;
//        this.price = price;
//        this.quantity = quantity;
//        this.img = img;
//        this.imgFile = imgFile;
//    }
//
//    public Product(Long productId, String product_Name, Long price, Long quantity, String img, MultipartFile imgFile) {
//        this.productId = productId;
//        Product_Name = product_Name;
//        this.price = price;
//        this.quantity = quantity;
//        this.img = img;
//        this.imgFile = imgFile;
//    }
    //    public Set<Cart> getCarts() {
//        return carts;
//    }
//
//    public void setCarts(Set<Cart> carts) {
//        this.carts = carts;
//    }
//
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String describe) {
        this.description = describe;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }

    public Set<Comment> getComment() {
        return comment;
    }

    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }
}
