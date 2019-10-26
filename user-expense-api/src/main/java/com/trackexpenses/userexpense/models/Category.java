package com.trackexpenses.userexpense.models;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {
	
	@Id
	private String categoryId;
	private String categoryName;
	private String categoryDescription;
	@ElementCollection
	private List<CategoryItem> categoryItems;
	
	public Category() {
		
	}
	
	public Category(String categoryId, String categoryName, String categoryDescription,
			List<CategoryItem> categoryItems) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.categoryItems = categoryItems;
	}
	
	// To create an expense category with category items
	public Category(String categoryId, String categoryName, String categoryDescription) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public List<CategoryItem> getCategoryItems() {
		return categoryItems;
	}

	public void setCategoryItems(List<CategoryItem> categoryItems) {
		this.categoryItems = categoryItems;
	}
	
	

}
