package com.JSBP.E_Notes_service.service.impl;

import com.JSBP.E_Notes_service.dto.CategoryDto;
import com.JSBP.E_Notes_service.dto.CategoryResponse;
import com.JSBP.E_Notes_service.entity.Category;
import com.JSBP.E_Notes_service.repository.CategoryRepository;
import com.JSBP.E_Notes_service.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
@Autowired
    private ModelMapper mapper;
    @Autowired
private CategoryRepository categoryRepo;
    @Override
    public Boolean saveCategory(CategoryDto categoryDto) {
//        using only DTO
        //        Category category = new Category();
//        category.setName(categoryDto.getName());
//        category.setDescription(categoryDto.getDescription());
//        category.setIsActive(categoryDto.getIsActive());
        
        
//        using ModelMapper
       Category category = mapper.map(categoryDto, Category.class);

        category.setIsDeleted(false);
        category.setCreatedBy(1);
        category.setCreatedOn(new Date());
        Category saveCategory = categoryRepo.save(category);
        if (ObjectUtils.isEmpty(saveCategory)){
            return false;
        }
        return true;
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepo.findAll();

        List<CategoryDto> categoryDtoList = categories.stream().map(cat -> mapper.map(cat, CategoryDto.class)).toList();
        return categoryDtoList;
    }

    @Override
    public List<CategoryResponse> getActiveCategory() {
        List<Category> categories = categoryRepo.findByIsActiveTrue();
        List<CategoryResponse>categoryList = categories.stream().map(cat -> mapper.map(cat,CategoryResponse.class)).toList();
        return categoryList;
    }
}
