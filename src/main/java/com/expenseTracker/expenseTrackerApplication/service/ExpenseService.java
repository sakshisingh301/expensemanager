package com.expenseTracker.expenseTrackerApplication.service;

import com.expenseTracker.expenseTrackerApplication.dto.expenseDTO;
import com.expenseTracker.expenseTrackerApplication.dto.totalIncomeDTO;
import com.expenseTracker.expenseTrackerApplication.model.Expense;

import java.util.Date;
import java.util.List;

public interface ExpenseService {

      List<Expense> findAll();

      List<totalIncomeDTO> TotalExpense();

      List<expenseDTO> findItemsAndAmount();

      void save(Expense expense);

      void deleteExpense(long id);

      Expense findById(Long id);

      Expense findByExpenseName(String expensename);

      List<Expense> sortingBasedOnExpenseAmount();

      List<expenseDTO> findExpenseByStartDateAndEndDate(Date startDate, Date endDate);

      List<Expense> sortingBasedOnDateAscendingOrder();

      List<Expense> sortingBasedOnDateDescendingOrder();

      List<expenseDTO> getMonthlyReportExpenses(int month);

      List<expenseDTO> getQuaterlyReport(int monthStart);

      List<expenseDTO> getMonthlyExpenseInSortedOrderbyAmountSpent(int month);

      List<expenseDTO> getMonthlyExpenseInSortedOrderbyRecentDate(int month);
}
