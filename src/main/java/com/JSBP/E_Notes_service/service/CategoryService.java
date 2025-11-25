package com.JSBP.E_Notes_service.service;

import com.JSBP.E_Notes_service.entity.Category;

import java.util.List;

public interface CategoryService {
    public Boolean saveCategory(Category category);
    public List<Category> getAllCategory();
}
