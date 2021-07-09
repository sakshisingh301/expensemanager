package com.expenseTracker.expenseTrackerApplication.dto;

import com.expenseTracker.expenseTrackerApplication.model.Expense;

import java.util.List;

public class totalIncomeDTO {

    private List<String> items;
    private int sum;

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
