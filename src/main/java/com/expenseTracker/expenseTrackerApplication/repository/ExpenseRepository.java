package com.expenseTracker.expenseTrackerApplication.repository;

import com.expenseTracker.expenseTrackerApplication.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Expense findByExpensename(@Param("expensename") String expensename);

    List<Expense> findByOrderByAmountAsc();

    List<Expense> findByOrderByDateAsc();

    List<Expense> findByOrderByDateDesc();



}
