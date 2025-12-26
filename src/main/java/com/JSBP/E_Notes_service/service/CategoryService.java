package com.JSBP.E_Notes_service.service;

import com.JSBP.E_Notes_service.dto.CategoryDto;
import com.JSBP.E_Notes_service.dto.CategoryResponse;
import com.JSBP.E_Notes_service.entity.Category;

import java.util.List;

public interface CategoryService {
    public Boolean saveCategory(CategoryDto categoryDto);
    public List<CategoryDto> getAllCategory();

    public  List<CategoryResponse> getActiveCategory();

    CategoryDto getCategoryById(Integer id);

    Boolean deleteCategory(Integer id);
}
