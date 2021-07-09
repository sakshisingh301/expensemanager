package com.expenseTracker.expenseTrackerApplication.dto;

import java.util.Date;

public class Report {


    private Long id;
    private Date date;
    private String cetegory;
    private int income;

    public Report(Long id, Date date, String cetegory, int income) {
        this.id = id;
        this.date = date;
        this.cetegory = cetegory;
        this.income = income;
    }

    public Report() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCetegory() {
        return cetegory;
    }

    public void setCetegory(String cetegory) {
        this.cetegory = cetegory;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }
}
