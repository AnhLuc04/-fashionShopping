package com.example.demo.service.role;

import com.example.demo.model.Product;
import com.example.demo.model.Role;
import com.example.demo.service.GeneralService;

public interface RoleService extends GeneralService {
    Role getById(Long id);

}
