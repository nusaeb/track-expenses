package com.trackexpenses.expense;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;
	
	public List<Expense> getAllExpenses(){
		List<Expense> expenses = new ArrayList<>();
		expenseRepository.findAll().forEach(expenses :: add);
		return expenses;
	}
	public List<Expense> getExpensesByUserId(String userId){
		
		List<Expense> userTotalAmountPerCategory = new ArrayList<>();
		expenseRepository.findByUserId(userId).forEach(userTotalAmountPerCategory :: add);
		return userTotalAmountPerCategory;
	}
	
	public List<Expense> getExpensesByUserAndCategory(String userId, String categoryId){
		
		List<Expense> userTotalAmountPerCategory = new ArrayList<>();
		expenseRepository.findByUserIdAndCategoryId(userId, categoryId).forEach(userTotalAmountPerCategory :: add);
		return userTotalAmountPerCategory;
	}
	
	public List<Expense> getTotalAmountPerCategory(String userId){
		
		List<Expense> userTotalAmountPerCategory = new ArrayList<>();
		expenseRepository.getTotalAmountPerCategory(userId).forEach(userTotalAmountPerCategory :: add);
		return userTotalAmountPerCategory;
	}
	
	public void addExpense(Expense expense) {
		expenseRepository.save(expense);
	}
	
	public void updateExpense(Expense expense) {
		expenseRepository.save(expense);
	}
	
	public void deleteExpense(Expense expense) {
		expenseRepository.delete(expense);
	}
}
