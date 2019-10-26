package com.trackexpenses.userexpense;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.trackexpenses.userexpense.apiresponses.ExpenseApiResponse;
import com.trackexpenses.userexpense.models.Category;
import com.trackexpenses.userexpense.models.Expense;
import com.trackexpenses.userexpense.models.UserExpense;
import com.trackexpenses.userexpense.models.UserInfo;

@RestController
@RequestMapping("/userexpenses")
public class UserExpenseController {
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/users/{userId}")
	public List<UserExpense> getExpenseDetailsByUser(@PathVariable String userId){
		
		List<UserExpense> userExpenses = new ArrayList<>();
		
		ExpenseApiResponse expenseApiResponse = restTemplate.getForObject("http://expense-api/expenses/users/" + userId + "/totalpercategory", ExpenseApiResponse.class);
		for (Expense expense : expenseApiResponse.getExpenses()) {
			UserExpense userExpense = new UserExpense();
			
			UserInfo userInfo = restTemplate.getForObject("http://user-api/users/" + userId, UserInfo.class);
			userExpense.setUserFirstName(userInfo.getFirstName());
			userExpense.setUserLastName(userInfo.getLastName());
			
			Category category = restTemplate.getForObject("http://category-api/categories/" + expense.getCategoryId(), Category.class);
			userExpense.setCategoryId(category.getCategoryId());
			userExpense.setCategoryName(category.getCategoryName());
			userExpense.setCategoryDescription(category.getCategoryDescription());
			
			userExpense.setAmount(expense.getAmount());
			
			userExpenses.add(userExpense);

		}
		
		return userExpenses;
	}
}
