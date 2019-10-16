package com.trackexpenses.category.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CategoryItem {

	@Id
	private String itemId;
	private String itemName;
	private String itemDescription;
	
	private String categoryId;

	public CategoryItem() {
		
	}
	
	public CategoryItem(String itemId, String itemName, String itemDescription, String categoryId) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.categoryId = categoryId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
