package com.solvd.construction.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Project implements Model {
    private Long id;
    private Timestamp startDate;
    private Timestamp finishDate;
    private Long client_id;
    private long floors;
    private boolean interiorWork;
    private BigDecimal budget;

    public Project(long id, Timestamp startDate, Timestamp finishDate, Long client_id,
                   long floors, boolean interiorWork, BigDecimal budget) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.client_id = client_id;
        this.floors = floors;
        this.interiorWork = interiorWork;
        this.budget = budget;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public boolean isInteriorWork() {
        return interiorWork;
    }

    public void setInteriorWork(boolean interiorWork) {
        this.interiorWork = interiorWork;
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

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public long getFloors() {
        return floors;
    }

    public void setFloors(long floors) {
        this.floors = floors;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
