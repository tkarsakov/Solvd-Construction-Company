package com.solvd.construction.model;

import java.math.BigDecimal;

public class Position implements Model {
    private Long id;
    private String positionName;
    private BigDecimal monthsSalary;

    public Position(Long id, String positionName, BigDecimal monthsSalary) {
        this.id = id;
        this.positionName = positionName;
        this.monthsSalary = monthsSalary;
    }

    public Position(String positionName, BigDecimal monthsSalary) {
        this.positionName = positionName;
        this.monthsSalary = monthsSalary;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
