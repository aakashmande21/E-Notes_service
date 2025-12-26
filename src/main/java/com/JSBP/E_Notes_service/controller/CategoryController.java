package com.JSBP.E_Notes_service.controller;

import com.JSBP.E_Notes_service.dto.CategoryDto;
import com.JSBP.E_Notes_service.dto.CategoryResponse;
import com.JSBP.E_Notes_service.entity.Category;
import com.JSBP.E_Notes_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save-category")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categoryDto) {
        Boolean saveCategory = categoryService.saveCategory(categoryDto);
        if (saveCategory) {
            return new ResponseEntity<>("Saved Successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Not Saved", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category")
    public ResponseEntity<?> getCategory() {
        List<CategoryDto> allCategory = categoryService.getAllCategory();
        if (CollectionUtils.isEmpty(allCategory)){
            return ResponseEntity.noContent().build();
        }else {
            return new ResponseEntity<>(allCategory,HttpStatus.OK);
        }
    }

    @GetMapping("/active-category")
    public ResponseEntity<?>ActiveCategory() {
        List<CategoryResponse> activeCategory = categoryService.getActiveCategory();
        if (CollectionUtils.isEmpty(activeCategory)){
            return ResponseEntity.noContent().build();
        }else {
            return new ResponseEntity<>(activeCategory,HttpStatus.OK);
        }
    }

}
