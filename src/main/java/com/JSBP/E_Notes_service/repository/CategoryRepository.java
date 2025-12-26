package com.JSBP.E_Notes_service.repository;

import com.JSBP.E_Notes_service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
//    Category save(Category category);
    List<Category>findByIsActiveTrue();
}
