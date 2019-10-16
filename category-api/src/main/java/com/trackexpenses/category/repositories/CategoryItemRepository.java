package com.trackexpenses.category.repositories;

import org.springframework.data.repository.CrudRepository;

import com.trackexpenses.category.models.Category;
import com.trackexpenses.category.models.CategoryItem;

public interface CategoryItemRepository extends CrudRepository<CategoryItem, String>{

}
