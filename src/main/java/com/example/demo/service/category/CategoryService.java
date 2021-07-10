package com.example.demo.service.category;


import com.example.demo.model.Category;
import com.example.demo.service.GeneralService;

import java.util.List;

public interface CategoryService extends GeneralService<Category> {
    List<Category> findAllCategoryByParentID(Long parentID);
}
