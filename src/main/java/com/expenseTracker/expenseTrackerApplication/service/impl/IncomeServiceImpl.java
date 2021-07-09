package com.expenseTracker.expenseTrackerApplication.service.impl;

import com.expenseTracker.expenseTrackerApplication.dto.Report;
import com.expenseTracker.expenseTrackerApplication.dto.allTransactionDTO;
import com.expenseTracker.expenseTrackerApplication.model.Expense;
import com.expenseTracker.expenseTrackerApplication.model.Income;
import com.expenseTracker.expenseTrackerApplication.repository.ExpenseRepository;
import com.expenseTracker.expenseTrackerApplication.repository.IncomeRepository;
import com.expenseTracker.expenseTrackerApplication.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    private Predicate<Expense> expensePredicate1;


    @Override
    public List<Income> findAllIncome() {
        return incomeRepository.findAll();
    }

    @Override
    public List<allTransactionDTO> AllTransaction(int month) {
        expensePredicate1= p -> {

            java.util.Date safeDate = new Date(p.getDate().getTime());

            return safeDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().getMonthValue()==month;
        };

        return expenseRepository.findAll()
                .stream()
                .filter(expensePredicate1)
                .map(this::convertToAllTransactionDTO)
                .limit(1)
                .collect(Collectors.toList());

    }

    @Override
    public List<Report> report() {
      Expense expense=new Expense();
     return   expenseRepository.findAll().stream().
             map(this::convertToReport)
             .collect(Collectors.toList());


    }
   private Report convertToReport(Expense expense)
    {
         Income income=new Income();
        Report report=new Report();
        report.setId(expense.getId());
        report.setDate(expense.getDate());
        report.setCetegory(expense.getCetegory());

        report.setIncome(income.getIncomeAmount());
        return report;
    }

    private  allTransactionDTO convertToAllTransactionDTO(Expense expense)
    {
        allTransactionDTO DTO=new allTransactionDTO();
        int getincome= incomeRepository.findAll().stream().mapToInt(Income::getIncomeAmount).sum();
        int getexpense=expenseRepository.findAll().stream().mapToInt(Expense::getAmount).sum();
        DTO.setIncome(getincome);
        DTO.setExpense(getexpense);
        DTO.setBalance(getincome-getexpense);
        return DTO;
    }


}
