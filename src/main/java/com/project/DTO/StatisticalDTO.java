package com.project.DTO;

import java.time.LocalDate;

public class StatisticalDTO {
    private String label;
    private float revenue;
    private float expense;
    private float profit;
    private LocalDate date;
    private String monthYear;
    private float values;

    public StatisticalDTO(float revenue, float expense, float profit, LocalDate date) {
        this.revenue = revenue;
        this.expense = expense;
        this.profit = profit;
        this.date = date;
    }

    public StatisticalDTO(float revenue, float expense, float profit, String monthYear) {
        this.revenue = revenue;
        this.expense = expense;
        this.profit = profit;
        this.monthYear = monthYear;
    }
    public StatisticalDTO(float revenue, float expense, float profit) {
        this.revenue = revenue;
        this.expense = expense;
        this.profit = profit;
    }

    public StatisticalDTO(float values, LocalDate date) {
        this.values = values;
        this.date = date;
    }
    public StatisticalDTO(String label, float values) {
        this.label = label;
        this.values = values;
    }

    public StatisticalDTO(float values, String monthYear) {
        this.values = values;
        this.monthYear = monthYear;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    public void setExpense(float expense) {
        this.expense = expense;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public void setValues(float values) {
        this.values = values;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public String getLabel() {
        return label;
    }

    public float getRevenue() {
        return revenue;
    }

    public float getExpense() {
        return expense;
    }

    public float getProfit() {
        return profit;
    }

    public LocalDate getDate() {
        return date;
    }

    public float getValues() {
        return values;
    }

    public String getMonthYear() {
        return monthYear;
    }
}
