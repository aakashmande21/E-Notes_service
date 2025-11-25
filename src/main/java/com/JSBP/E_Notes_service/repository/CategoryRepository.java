package com.JSBP.E_Notes_service.repository;

import com.JSBP.E_Notes_service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category save(Category category);
}
