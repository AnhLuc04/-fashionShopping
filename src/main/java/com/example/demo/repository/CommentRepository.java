package com.example.demo.repository;

import com.example.demo.model.AppUser;
import com.example.demo.model.Comment;
import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Iterable<Comment> getAllByProduct(Product product);
    Iterable<Comment> getAllByAppUser(AppUser user);
}
