package com.trackexpenses.userexpense.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long expenseId;
	private String userId;
	private String categoryId;
	private String categoryItemId;
	private String categorySubItemId;
	private Double amount;
	private LocalDate occurrenceDate;
	
	public Expense() {
		
	}
	
	public Expense(String userId, String categoryId, String categoryItemId, String categorySubItemId,
			Double amount, LocalDate occurrenceDate) {
		super();
		this.userId = userId;
		this.categoryId = categoryId;
		this.categoryItemId = categoryItemId;
		this.categorySubItemId = categorySubItemId;
		this.amount = amount;
		this.occurrenceDate = occurrenceDate;
	}
	
	public Expense(String categoryId, Double amount) {
		super();
		this.categoryId = categoryId;
		this.amount = amount;
	}

	public Long getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Long expenseId) {
		this.expenseId = expenseId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryItemId() {
		return categoryItemId;
	}

	public void setCategoryItemId(String categoryItemId) {
		this.categoryItemId = categoryItemId;
	}

	public String getCategorySubItemId() {
		return categorySubItemId;
	}

	public void setCategorySubItemId(String categorySubItemId) {
		this.categorySubItemId = categorySubItemId;
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
