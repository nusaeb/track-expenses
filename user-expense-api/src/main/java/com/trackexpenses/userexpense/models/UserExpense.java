package com.trackexpenses.userexpense.models;

import java.time.LocalDate;

public class UserExpense {

	private String userId;
	private String userFirstName;
	private String userLastName;
	private String categoryId;
	private String categoryName;
	private String categoryDescription;
	private String itemId;
	private String itemName;
	private String itemDescription;
	private Double amount;
	private LocalDate occurrenceDate;
	
	public UserExpense() {
		
	}

	public UserExpense(String userId, String userFirstName, String userLastName, String categoryId, String categoryName,
			String categoryDescription, String itemId, String itemName, String itemDescription, Double amount,
			LocalDate occurrenceDate) {
		super();
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.amount = amount;
		this.occurrenceDate = occurrenceDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getOccurrenceDate() {
		return occurrenceDate;
	}

	public void setOccurrenceDate(LocalDate occurrenceDate) {
		this.occurrenceDate = occurrenceDate;
	}
	
}