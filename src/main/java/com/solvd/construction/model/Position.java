package com.solvd.construction.model;

import java.math.BigDecimal;

public class Position {
    private final int id;
    private String positionName;
    private BigDecimal monthsSalary;

    public Position(int id, String positionName, BigDecimal monthsSalary) {
        this.id = id;
        this.positionName = positionName;
        this.monthsSalary = monthsSalary;
    }

    public int getId() {
        return id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public BigDecimal getMonthsSalary() {
        return monthsSalary;
    }

    public void setMonthsSalary(BigDecimal monthsSalary) {
        this.monthsSalary = monthsSalary;
    }
}
