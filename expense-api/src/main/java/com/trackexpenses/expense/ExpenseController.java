package com.trackexpenses.expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;
	
	@RequestMapping("/users/{userId}")
	public ExpenseApiResponse getUserExpenses(@PathVariable String userId){
		
		ExpenseApiResponse expenseApiResponse = new ExpenseApiResponse();
		expenseApiResponse.setExpenses(expenseService.getExpensesByUserId(userId));
		return expenseApiResponse;
	}
	
	@RequestMapping("/users/{userId}/categories/{categoryId}")
	public ExpenseApiResponse getUserCategoryExpenses(@PathVariable String userId, @PathVariable String categoryId){
		
		ExpenseApiResponse expenseApiResponse = new ExpenseApiResponse();
		expenseApiResponse.setExpenses(expenseService.getExpensesByUserAndCategory(userId, categoryId));
		return expenseApiResponse;
	}
	
	@RequestMapping("/users/{userId}/totalpercategory")
	public ExpenseApiResponse getTotalAmountPerCategory(@PathVariable String userId) {
		
		ExpenseApiResponse expenseApiResponse = new ExpenseApiResponse();
		expenseApiResponse.setExpenses(expenseService.getTotalAmountPerCategory(userId));
		return expenseApiResponse;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/users/{userId}")
	public void addExpense(@RequestBody Expense expense, @PathVariable String userId) {
		expense.setUserId(userId);
		expenseService.addExpense(expense);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/users/{userId}")
	public void updateExpense(@RequestBody Expense expense) {
		expenseService.updateExpense(expense);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}")
	public void deleteExpense(@RequestBody Expense expense) {
		expenseService.deleteExpense(expense);
	}
}
