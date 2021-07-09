package com.expenseTracker.expenseTrackerApplication.repository;

import com.expenseTracker.expenseTrackerApplication.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income,Long> {


}
