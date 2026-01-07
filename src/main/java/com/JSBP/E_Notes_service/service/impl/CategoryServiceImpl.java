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
import java.util.Optional;

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
//       Category category = mapper.map(categoryDto, Category.class);
//
//       if(ObjectUtils.isEmpty(category.getId()))
//       {
//           category.setIsDeleted(false);
//           category.setCreatedBy(1);
//           category.setCreatedOn(new Date());
//       }
//       else
//       {
//                    updateCategory(category);
//       }
//        Category saveCategory = categoryRepo.save(category);
//        if (ObjectUtils.isEmpty(saveCategory)){
//            return false;
//        }
//        return true;


        Category category = mapper.map(categoryDto, Category.class);
        if (category.getId() == null) {
            category.setIsDeleted(false);
            category.setCreatedBy(1);
            category.setCreatedOn(new Date());
        } else {
            updateCategory(category);
        }
        Category saved = categoryRepo.save(category);
        return saved != null;
    }
    private void updateCategory(Category category){
        Optional<Category> findById = categoryRepo.findById(category.getId());

        if (findById.isPresent())
        {
            Category existCategory = findById.get();
            category.setCreatedBy(existCategory.getCreatedBy());
            category.setCreatedOn(existCategory.getCreatedOn());
            category.setIsDeleted(existCategory.getIsDeleted());

            category.setUpdatedBy(1);
            category.setUpdatedOn(new Date());
        }
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepo.findByIsDeletedFalse();

        List<CategoryDto> categoryDtoList = categories.stream().map(cat -> mapper.map(cat, CategoryDto.class)).toList();
        return categoryDtoList;
    }

    @Override
    public List<CategoryResponse> getActiveCategory() {
        List<Category> categories = categoryRepo.findByIsActiveTrueAndIsDeletedFalse();
        List<CategoryResponse> categoryList = categories.stream().map(cat -> mapper.map(cat,CategoryResponse.class)).toList();
        return categoryList;
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Optional<Category> findByCategory = categoryRepo.findByIdAndIsDeletedFalse(id);

        if (findByCategory.isPresent()){
            Category category = findByCategory.get();
            return mapper.map(category, CategoryDto.class);
        }
        return null;
    }


    @Override
    public Boolean deleteCategory(Integer id) {
        Optional<Category> findByCategory = categoryRepo.findById(id);

        if (findByCategory.isPresent()){
            Category category = findByCategory.get();
            category.setIsDeleted(true);
            categoryRepo.save(category);
            return true;
        }
        return false;
    }
}
