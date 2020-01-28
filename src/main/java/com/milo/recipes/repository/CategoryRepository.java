package com.milo.recipes.repository;

import com.milo.recipes.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
