package com.example.demo.service.product;



import com.example.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public interface ProductService {

    Iterable<Product> findAll(Sort sort);


    Page<Product> findAll(Pageable pageable);

    void save(Product product);


//  <S extends Product> Iterable<S> saveAll(Iterable<S> entities);


    Optional<Product> findById(Long id);


    boolean existsById(Long id);


    Iterable<Product> findAll();


    long count();


    void deleteById(Long Long);


    void delete(Product product);


//   void deleteAll(Iterable<? extends Product> entities);


    void deleteAll();

    Page<Product> findAllByNameContaining(String name , Pageable pageable);
}
