package com.solvd.construction.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Project {
    private final int id;
    private Timestamp startDate;
    private Timestamp finishDate;
    private Client client;
    private int floors;
    private boolean interiorWork;
    private BigDecimal budget;

    public Project(int id, Timestamp startDate, Timestamp finishDate, Client client,
                   int floors, boolean interiorWork, BigDecimal budget) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.client = client;
        this.floors = floors;
        this.interiorWork = interiorWork;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Timestamp finishDate) {
        this.finishDate = finishDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public boolean isInteriorWork() {
        return interiorWork;
    }

    public void setInteriorWork(boolean interiorWork) {
        this.interiorWork = interiorWork;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
