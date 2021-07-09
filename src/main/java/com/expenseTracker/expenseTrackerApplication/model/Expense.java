package com.expenseTracker.expenseTrackerApplication.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Expense {
    @Id
    private Long id;
    private String expensename;
    private int amount;
    private String note;
    private String cetegory;

    @Temporal(TemporalType.DATE)
    private Date date;

    public Expense(Long id, String expensename, int amount, String note, Date date,String cetegory) {
        this.id = id;
        this.expensename = expensename;
        this.amount = amount;
        this.note = note;
        this.date = date;
        this.cetegory=cetegory;
    }

    public Expense() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpensename() {
        return expensename;
    }

    public void setExpensename(String expensename) {
        this.expensename = expensename;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", expensename='" + expensename + '\'' +
                ", amount=" + amount +
                ", note='" + note + '\'' +
                ", cetegory='" + cetegory + '\'' +
                ", date=" + date +
                '}';
    }
}
