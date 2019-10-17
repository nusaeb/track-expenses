package com.trackexpenses.userexpenseinfo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ExpenseInfoRepository extends CrudRepository<ExpenseInfo, Long>{

	public List<ExpenseInfo> findByUserId(String userId);
	
	public List<ExpenseInfo> findByUserIdAndCategoryId(String userId, String categoryId);
}
