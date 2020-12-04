package com.example.demo.service.product;



import com.example.demo.model.Product;
import com.example.demo.service.GeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public interface ProductService extends GeneralService<Product> {

    Iterable<Product> findAll(Sort sort);

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllByNameContaining(String name , Pageable pageable);
}
