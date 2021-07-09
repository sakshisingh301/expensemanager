package com.expenseTracker.expenseTrackerApplication.controller;


import com.expenseTracker.expenseTrackerApplication.dto.Report;
import com.expenseTracker.expenseTrackerApplication.dto.allTransactionDTO;
import com.expenseTracker.expenseTrackerApplication.model.Income;
import com.expenseTracker.expenseTrackerApplication.repository.IncomeRepository;
import com.expenseTracker.expenseTrackerApplication.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private IncomeService incomeService;

     @GetMapping("/allIncome")
    public List<Income> income()
    {
    return incomeService.findAllIncome();
    }


    // get monthly -total income,expense,balance
    @GetMapping("transaction/{month}")
    public List<allTransactionDTO> allTransaction(@PathVariable int month)
    {
        return incomeService.AllTransaction(month);
    }

    @GetMapping("/report")
    public List <Report> report()
    {
        return incomeService.report();
    }

}
