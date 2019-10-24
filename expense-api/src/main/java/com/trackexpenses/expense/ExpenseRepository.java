package com.trackexpenses.expense;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ExpenseRepository extends CrudRepository<Expense, Long>{

	public List<Expense> findByUserId(String userId);
	
	public List<Expense> findByUserIdAndCategoryId(String userId, String categoryId);
	
	/*
	 * Get total expenses per category for a particular user.
	 */
	@Query(value = "SELECT new Expense(e.categoryId, SUM(e.amount)) from Expense e "
			+ "WHERE e.userId = :userId "
			+ "GROUP BY e.categoryId ORDER BY e.categoryId ASC")
	public List<Expense> getTotalAmountPerCategory(@Param("userId") String userId);
}
