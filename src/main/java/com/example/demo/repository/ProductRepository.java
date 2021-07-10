package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
//    Page<Product> findAllByUserNameContaining(String name , Pageable pageable);
    Page<Product>findAllByNameContaining(String name , Pageable pageable);
    Product findByName(Product product);
    Product findByProductId(Long product_id);
//    Product getProductBy
}
