package com.expenseTracker.expenseTrackerApplication.controller;

import java.util.Date;
import java.util.Optional;

import com.expenseTracker.expenseTrackerApplication.dto.expenseDTO;
import com.expenseTracker.expenseTrackerApplication.dto.totalIncomeDTO;
import com.expenseTracker.expenseTrackerApplication.model.Expense;
import com.expenseTracker.expenseTrackerApplication.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    //list of expense object
    @GetMapping("/expenses")
    public List<Expense> home() {
        return expenseService.findAll();
    }

    //total expense
    @GetMapping("/totalExpense")
    public List<totalIncomeDTO> totalExpense() {
       return  expenseService.TotalExpense();
    }

    //list of item bought and their amount
    @GetMapping("/listOfExpenses")
    public List<expenseDTO> ListOfExpenses() {

        return expenseService.findItemsAndAmount();
    }

    //adding expense
    @PostMapping("/expense")
    public String addExpense(@RequestBody Expense expense) {

        expenseService.save(expense);
        return "EXPENSE ADDED";
    }

    //delete expense
    @DeleteMapping("record/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "RECORD DELETED";
    }

    //edit expense
    @PutMapping("/edit/{id}")
    public ResponseEntity<Object> edit(@RequestBody Expense expense, @PathVariable Long id) {
        Optional<Expense> expenseOptional = Optional.ofNullable(expenseService.findById(id));

        if (!expenseOptional.isPresent())
            return ResponseEntity.notFound().build();
        expense.setId(id);
        expenseService.save(expense);
        return ResponseEntity.noContent().build();
    }


    //Filter by expensename
    @GetMapping("expense-name/{expensename}")
    public Object findByExpensename(@PathVariable String expensename) {
        Optional<Expense> expenseOptional = Optional.ofNullable
                (expenseService.findByExpenseName(expensename));
        if (!expenseOptional.isPresent()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return expenseService.findByExpenseName(expensename);

    }


    //sort by amount
    @GetMapping("sort-by-amount")
    public List<Expense> sortByAmount() {
        return expenseService.sortingBasedOnExpenseAmount();
    }

    // find expenses given start date and end date
    @GetMapping("/findExpenseByStartDateEndDate/{startDate}/{endDate}")
    public List<expenseDTO> findExpense(
            @PathVariable(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @PathVariable(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return expenseService.findExpenseByStartDateAndEndDate(startDate, endDate);

    }


    //find the list of expense in ascending order of date
    @GetMapping("sort-by-Date-asc")
    public List<Expense> sortByDateAsc() {
        return expenseService.sortingBasedOnDateAscendingOrder();
    }

    //find the list of expense in descending order of date
    @GetMapping("sort-by-Date-desc")
    public List<Expense> sortByDateDesc() {
        return expenseService.sortingBasedOnDateDescendingOrder();
    }


    //get monthly report by selecting month only expensename and amount spent
    @GetMapping("/monthlyReport/{month}")
    public Object monthlyReport(@PathVariable int month)
    {
        List<expenseDTO> expenseOptional =
                expenseService.getMonthlyReportExpenses(month);
        if (expenseOptional.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return expenseService.getMonthlyReportExpenses(month);
    }

    //get quaterly report only expensename and amount spent
   @GetMapping("/quaterlyReport/{monthStart}")
    public Object quaterlyReport(@PathVariable int monthStart)
    {
        List<expenseDTO> expenseOptional =
                expenseService.getQuaterlyReport(monthStart);
        if (expenseOptional.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return expenseService.getQuaterlyReport(monthStart);
    }


    //monthy expense report based on most costly item bought
    @GetMapping("/monthlyReportInSortedOrderByAmount/{month}")
    public Object monthlyReportInSortedOrder(@PathVariable int month)
    {
        List<expenseDTO> expenseOptional =
                expenseService.getMonthlyExpenseInSortedOrderbyAmountSpent(month);
        if (expenseOptional.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return expenseService.getMonthlyExpenseInSortedOrderbyAmountSpent(month);
    }


    //monthy expense report based on recent Date
    @GetMapping("/monthlyReportInSortedOrderByDate/{month}")
    public Object monthlyReportInSortedOrderbyDate(@PathVariable int month)
    {
        List<expenseDTO> expenseOptional =
                expenseService.getMonthlyExpenseInSortedOrderbyRecentDate(month);
        if (expenseOptional.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        return expenseService.getMonthlyExpenseInSortedOrderbyRecentDate(month);
    }


}
