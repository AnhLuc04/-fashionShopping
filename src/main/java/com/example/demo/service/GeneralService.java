package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.Optional;

public interface GeneralService<T> {

    Iterable<T> findAll();

    Optional<Product> findById(Long id);

    T save(T t);

    void remove(Long id);
}
