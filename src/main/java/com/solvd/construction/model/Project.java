package com.solvd.construction.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Project {
    private final long id;
    private Timestamp startDate;
    private Timestamp finishDate;
    private Client client;
    private long floors;
    private boolean longeriorWork;
    private BigDecimal budget;

    public Project(long id, Timestamp startDate, Timestamp finishDate, Client client,
                   long floors, boolean longeriorWork, BigDecimal budget) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.client = client;
        this.floors = floors;
        this.longeriorWork = longeriorWork;
        this.budget = budget;
    }

    public long getId() {
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

    public long getFloors() {
        return floors;
    }

    public void setFloors(long floors) {
        this.floors = floors;
    }

    public boolean islongeriorWork() {
        return longeriorWork;
    }

    public void setlongeriorWork(boolean longeriorWork) {
        this.longeriorWork = longeriorWork;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
