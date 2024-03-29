package com.example.demo.service.comment;

import com.example.demo.model.AppUser;
import com.example.demo.model.Comment;
import com.example.demo.model.Product;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.getOne(id);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void remove(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Iterable<Comment> getAllByProduct(Product product) {
        return commentRepository.getAllByProduct(product);
    }

    @Override
    public Iterable<Comment> getAllByAppUser(AppUser user) {
        return commentRepository.getAllByAppUser(user);
    }
}
