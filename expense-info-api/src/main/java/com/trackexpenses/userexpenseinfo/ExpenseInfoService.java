package com.trackexpenses.userexpenseinfo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseInfoService {

	@Autowired
	private ExpenseInfoRepository expenseInfoRepository;
	
	public List<ExpenseInfo> getAllExpenseInfo(){
		List<ExpenseInfo> expenseInfo = new ArrayList<>();
		expenseInfoRepository.findAll().forEach(expenseInfo::add);
		return expenseInfo;
	}
	public List<ExpenseInfo> getExpenseInfoByUserId(String userId){
		
		List<ExpenseInfo> userExpenseInfo = new ArrayList<>();
		expenseInfoRepository.findByUserId(userId).forEach(userExpenseInfo::add);
		return userExpenseInfo;
	}
	
	public List<ExpenseInfo> getExpenseInfoByUserAndCategoryId(String userId, String categoryId){
		
		List<ExpenseInfo> userCategoryExpenseInfo = new ArrayList<>();
		expenseInfoRepository.findByUserIdAndCategoryId(userId, categoryId).forEach(userCategoryExpenseInfo::add);
		return userCategoryExpenseInfo;
	}
	
	public void addExpenseInfo(ExpenseInfo expenseInfo) {
		expenseInfoRepository.save(expenseInfo);
	}
	
	public void updateExpenseInfo(ExpenseInfo expenseInfo) {
		expenseInfoRepository.save(expenseInfo);
	}
	
	public void deleteExpenseInfo(ExpenseInfo expenseInfo) {
		expenseInfoRepository.delete(expenseInfo);
	}
}
