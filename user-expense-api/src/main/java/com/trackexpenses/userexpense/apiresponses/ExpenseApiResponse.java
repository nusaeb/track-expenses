package com.trackexpenses.userexpense.apiresponses;

import java.util.List;

import com.trackexpenses.userexpense.models.Expense;

/** 
 * This wrapper class is used to avoid returning a list as a response to calls to the expense API endpoints.
 * */
public class ExpenseApiResponse {

	private List<Expense> expenses;

	public ExpenseApiResponse() {
		
	}
	
	public ExpenseApiResponse(List<Expense> expenses) {
		super();
		this.expenses = expenses;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}
	
}
