package com.expenseTracker.expenseTrackerApplication.service;

import com.expenseTracker.expenseTrackerApplication.dto.Report;
import com.expenseTracker.expenseTrackerApplication.dto.allTransactionDTO;
import com.expenseTracker.expenseTrackerApplication.model.Income;

import java.util.List;

public interface IncomeService {

    List<Income> findAllIncome();

    List<allTransactionDTO> AllTransaction(int month);

    List<Report> report();


}
