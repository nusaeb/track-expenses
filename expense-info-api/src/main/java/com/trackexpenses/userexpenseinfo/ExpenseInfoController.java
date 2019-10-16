package com.trackexpenses.userexpenseinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseInfoController {

	@Autowired
	private ExpenseInfoService expenseInfoService;
	
	@RequestMapping("/expenseinfo/{userId}")
	public List<ExpenseInfo> getUserExpenses(@PathVariable String userId){
		
		return expenseInfoService.getExpenseInfoByUserId(userId);
	}
	
	@RequestMapping("/expenseinfo/{userId}/{categoryId}")
	public List<ExpenseInfo> getUserCategoryExpenses(@PathVariable String userId, @PathVariable String categoryId){
		
		return expenseInfoService.getExpenseInfoByUserAndCategoryId(userId, categoryId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/expenseinfo/{userId}")
	public void addExpenseInfo(@RequestBody ExpenseInfo expenseInfo, @PathVariable String userId) {
		expenseInfo.setUserId(userId);
		expenseInfoService.addExpenseInfo(expenseInfo);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/expenseinfo/{userId}")
	public void updateExpenseInfo(@RequestBody ExpenseInfo expenseInfo) {
		expenseInfoService.updateExpenseInfo(expenseInfo);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/expenseinfo/{userId}")
	public void deleteExpenseInfo(@RequestBody ExpenseInfo expenseInfo) {
		expenseInfoService.deleteExpenseInfo(expenseInfo);
	}
}
