package com.solvd.construction.model;

public class Employee {
    private long id;
    private String firstName;
    private String lastName;
    private Long positionId;

    public Employee(String firstName, String lastName, Long positionId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.positionId = positionId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
}
