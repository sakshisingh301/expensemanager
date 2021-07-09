package com.expenseTracker.expenseTrackerApplication.service.impl;

import com.expenseTracker.expenseTrackerApplication.dto.expenseDTO;
import com.expenseTracker.expenseTrackerApplication.dto.totalIncomeDTO;
import com.expenseTracker.expenseTrackerApplication.model.Expense;
import com.expenseTracker.expenseTrackerApplication.repository.ExpenseRepository;
import com.expenseTracker.expenseTrackerApplication.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    private Predicate<Expense> expensePredicate;


    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public List <totalIncomeDTO> TotalExpense() {
        return  expenseRepository.findAll().stream().
                limit(1)
                .map(this::convertTosumTotalDto)
                .collect(Collectors.toList());
    }

    private totalIncomeDTO convertTosumTotalDto(Expense expense)
    {
        totalIncomeDTO DTO=new totalIncomeDTO();
        DTO.setItems(expenseRepository.findAll().stream()
                .map(Expense::getExpensename).collect(Collectors.toList()));
       DTO.setSum(expenseRepository.findAll().stream().mapToInt(Expense::getAmount).sum());
        return DTO;
    }

    @Override
    public List<expenseDTO> findItemsAndAmount() {

        return expenseRepository
                .findAll()
                .stream()
                .map(this::convertToListOfexpenseAndAmount )
                .collect(Collectors.toList());


    }

    private expenseDTO convertToListOfexpenseAndAmount(Expense expense) {

        expenseDTO listOfexpenseAndAmount = new expenseDTO();
        listOfexpenseAndAmount.setItems(String.valueOf(expense.getExpensename()));
        listOfexpenseAndAmount.setAmountSpent(expense.getAmount());
        return listOfexpenseAndAmount;
    }

    @Override
    public void save(Expense expense) {
        expenseRepository.save(expense);

    }

    @Override
    public void deleteExpense(long id) {
        expenseRepository.deleteById(id);

    }

    @Override
    public Expense findById(Long id) {
        if (expenseRepository.findById(id).isPresent()) {
            return expenseRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public Expense findByExpenseName(String expensename) {
        return expenseRepository.findByExpensename(expensename);


    }

    @Override
    public List<Expense> sortingBasedOnExpenseAmount() {
        return expenseRepository.findByOrderByAmountAsc();
    }


    @Override
    public List<expenseDTO> findExpenseByStartDateAndEndDate(Date startDate, Date endDate) {
        return expenseRepository.findAll().stream().
                filter(p -> p.getDate().after(startDate) && p.getDate().before(endDate)
                        || p.getDate().equals(startDate) || p.getDate().equals(endDate))
                .map(this::convertToListOfexpenseAndAmount)
                .collect(Collectors.toList());

    }

    @Override
    public List<Expense> sortingBasedOnDateAscendingOrder() {
        return expenseRepository.findByOrderByDateAsc();
    }

    @Override
    public List<Expense> sortingBasedOnDateDescendingOrder() {
        return expenseRepository.findByOrderByDateDesc();
    }

    @Override
    public List<expenseDTO> getMonthlyReportExpenses(int month) {
        expensePredicate = p -> {

                    java.util.Date safeDate = new Date(p.getDate().getTime());

           return safeDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().getMonthValue()==month;
        };
        return expenseRepository.findAll().stream()
                .filter(expensePredicate)
                .map(this::convertToListOfexpenseAndAmount)
                .collect(Collectors.toList());

    }

    @Override
    public List<expenseDTO> getQuaterlyReport(int monthStart) {
        expensePredicate = p -> {

            java.util.Date safeDate = new Date(p.getDate().getTime());

            return safeDate.toInstant().atZone(ZoneId.systemDefault()).
                    toLocalDateTime().getMonthValue()==monthStart ||safeDate.toInstant().atZone(ZoneId.systemDefault()).
                    toLocalDateTime().getMonthValue()==monthStart+1 ||safeDate.toInstant().atZone(ZoneId.systemDefault()).
                    toLocalDateTime().getMonthValue()==monthStart+2 ||safeDate.toInstant().atZone(ZoneId.systemDefault()).
                    toLocalDateTime().getMonthValue()==monthStart+3;
        };
        return expenseRepository.findAll().stream()
                .filter(expensePredicate)
                .map(this::convertToListOfexpenseAndAmount)
                .collect(Collectors.toList());


    }

    @Override
    public List<expenseDTO> getMonthlyExpenseInSortedOrderbyAmountSpent(int month) {
        expensePredicate = p -> {

            java.util.Date safeDate = new Date(p.getDate().getTime());

            return safeDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().getMonthValue()==month;
        };
        return expenseRepository.findAll().stream()
                .filter(expensePredicate)
                .sorted(Comparator.comparingInt(Expense::getAmount).reversed())
                .map(this::convertToListOfexpenseAndAmount)
                .collect(Collectors.toList());
    }

    @Override
    public List<expenseDTO> getMonthlyExpenseInSortedOrderbyRecentDate(int month) {
        expensePredicate = p -> {

            java.util.Date safeDate = new Date(p.getDate().getTime());

            return safeDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().getMonthValue()==month;
        };


        return expenseRepository.findAll().stream()
                .filter(expensePredicate)
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .map(this::convertToListOfexpenseAndAmount)
                .collect(Collectors.toList());
    }


}
