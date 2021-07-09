package com.expenseTracker.expenseTrackerApplication.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Income {

    @Id
    private Long incomeId;
    private int incomeAmount;
    private String cetegory;

    @Temporal(value = TemporalType.DATE)
    private Date date1;
    private String description;
    private String paymentType;

    public Income() {
    }

    public Income(Long incomeId, int incomeAmount, String cetegory, Date date1, String description, String paymentType) {
        this.incomeId = incomeId;
        this.incomeAmount = incomeAmount;
        this.cetegory = cetegory;
        this.date1 = date1;
        this.description = description;
        this.paymentType = paymentType;
    }

    public Long getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Long incomeId) {
        this.incomeId = incomeId;
    }

    public int getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(int incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public String getCetegory() {
        return cetegory;
    }

    public void setCetegory(String cetegory) {
        this.cetegory = cetegory;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
