package com.JSBP.E_Notes_service.controller;

import com.JSBP.E_Notes_service.dto.CategoryDto;
import com.JSBP.E_Notes_service.dto.CategoryResponse;
import com.JSBP.E_Notes_service.entity.Category;
import com.JSBP.E_Notes_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categoryDto) {
        categoryDto.setId(null);
        Boolean saveCategory = categoryService.saveCategory(categoryDto);
        if (saveCategory) {
            return new ResponseEntity<>("Saved Successfully", HttpStatus.CREATED);
        }
            return new ResponseEntity<>("Not Saved", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/")
    public ResponseEntity<?> getCategory() {
        List<CategoryDto> allCategory = categoryService.getAllCategory();
        if (CollectionUtils.isEmpty(allCategory)){
            return ResponseEntity.noContent().build();
        }else {
            return new ResponseEntity<>(allCategory,HttpStatus.OK);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<?>ActiveCategory() {
        List<CategoryResponse> activeCategory = categoryService.getActiveCategory();
        if (CollectionUtils.isEmpty(activeCategory)){
            return ResponseEntity.noContent().build();
        }else {
            return new ResponseEntity<>(activeCategory,HttpStatus.OK);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?>getCategoryDetailsById(@PathVariable Integer id){
    CategoryDto categoryDto = categoryService.getCategoryById(id);
    if (ObjectUtils.isEmpty(categoryDto)){
        return new ResponseEntity<>("Category not found with Id: "+id, HttpStatus.NOT_FOUND);
    }
    else {
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryDetailsById(@PathVariable Integer id){
    Boolean deleted = categoryService.deleteCategory(id);
    if (deleted){
        return  new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }
    else {
        return new ResponseEntity<>("Category Not Deleted", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

}
