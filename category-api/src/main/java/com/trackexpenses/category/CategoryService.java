package com.trackexpenses.category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackexpenses.category.models.*;
import com.trackexpenses.category.repositories.CategoryItemRepository;
import com.trackexpenses.category.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryItemRepository categoryItemRepository;
	
	public List<Category> getAllCategories(){
		List<Category> categories = new ArrayList<>();
		categoryRepository.findAll().forEach(categories::add);
		return categories;
	}
	
	public Category getCategory(String categoryId) {
		return categoryRepository.findById(categoryId).get();
	}
	
	public void addCategory(Category category) {
		List<CategoryItem> categoryItems = category.getCategoryItems();
		// If category has item(s)
		if(categoryItems != null && !categoryItems.isEmpty()) {
			// Add each item under the category
			for(CategoryItem categoryItem :categoryItems){
				categoryItem.setCategoryId(category.getCategoryId());
				categoryItemRepository.save(categoryItem);
			}
		}
		// Add the category
		categoryRepository.save(category);
	}
	
	public void updateCategory(Category category) {
		// Updating only category. If any category item needs to be updated, that is done through
		// CategoryItem repository. That's why categoryItems is set to an empty list
		List<CategoryItem> categoryItems = new ArrayList<>();
		category.setCategoryItems(categoryItems);
		// Update the category
		categoryRepository.save(category);
	}
	
	public void deleteCategory(String categoryId) {
		categoryRepository.deleteById(categoryId);
	}
}
