package com.example.demo.service.comment;

import com.example.demo.model.AppUser;
import com.example.demo.model.Comment;
import com.example.demo.model.Product;
import com.example.demo.service.GeneralService;

public interface CommentService extends GeneralService<Comment> {
    Iterable<Comment> getAllByProduct(Product product);
    Iterable<Comment> getAllByAppUser(AppUser user);
}
