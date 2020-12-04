package com.example.demo.service.role;

import com.example.demo.model.Product;
import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Iterable findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Object save(Object o) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }


    @Override
    public Role getById(Long id) {
        return roleRepository.getById(id);
    }
}
