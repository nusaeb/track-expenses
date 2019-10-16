package com.trackexpenses.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trackexpenses.category.models.Category;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/categories")
	public List<Category> getAllCategories(){
		
		return categoryService.getAllCategories();
	}
	
	@RequestMapping("/categories/{categoryId}")
	public Category getCategory(@PathVariable String categoryId){
		
		return categoryService.getCategory(categoryId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value= "/categories")
	public void addCategory(@RequestBody Category category) {
		
		categoryService.addCategory(category);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value= "/categories/{categoryId}")
	public void updatedCategory(@RequestBody Category category, @PathVariable String categoryId) {
		
		categoryService.updateCategory(category);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value= "/categories/{categoryId}")
	public void deleteCategory(@PathVariable String categoryId) {
		
		categoryService.deleteCategory(categoryId);
	}
}
