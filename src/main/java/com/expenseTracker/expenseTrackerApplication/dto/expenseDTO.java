package com.expenseTracker.expenseTrackerApplication.dto;

public class expenseDTO {

    private String items;
    private int  amountSpent;

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public int getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(int amountSpent) {
        this.amountSpent = amountSpent;
    }
}
