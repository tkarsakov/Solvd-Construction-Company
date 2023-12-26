package com.solvd.construction.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Project implements Model {
    private Long id;
    private Timestamp startDate;
    private Timestamp finishDate;
    private Long client_id;
    private Client client;
    private List<Employee> employeeList;
    private List<ProjectMaterial> projectMaterials;
    private Long deadline;
    private Long floors;
    private Boolean interiorWork;
    private BigDecimal budget;

    public Project(Long id, Timestamp startDate, Timestamp finishDate, Long client_id,
                   Long floors, Boolean interiorWork, BigDecimal budget) {
        this.id = id;
        this.startDate = startDate;
        this.client_id = client_id;
        this.finishDate = finishDate;
        this.interiorWork = interiorWork;
        this.floors = floors;
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

    public void setFloors(Long floors) {
        this.floors = floors;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<ProjectMaterial> getProjectMaterials() {
        return projectMaterials;
    }

    public void setProjectMaterials(List<ProjectMaterial> projectMaterials) {
        this.projectMaterials = projectMaterials;
    }

    public Long getDeadline() {
        return deadline;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }

    public Boolean getInteriorWork() {
        return interiorWork;
    }

    public void setInteriorWork(boolean interiorWork) {
        this.interiorWork = interiorWork;
    }

    public void setInteriorWork(Boolean interiorWork) {
        this.interiorWork = interiorWork;
    }
}
