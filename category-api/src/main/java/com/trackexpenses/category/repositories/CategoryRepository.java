package com.trackexpenses.category.repositories;

import org.springframework.data.repository.CrudRepository;

import com.trackexpenses.category.models.Category;

public interface CategoryRepository extends CrudRepository<Category, String>{

}
