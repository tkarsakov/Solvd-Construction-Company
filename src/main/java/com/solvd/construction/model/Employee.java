package com.solvd.construction.model;

public class Employee implements Model {
    private Long id;
    private String firstName;
    private String lastName;
    private Long positionId;
    private Position position;

    public Employee(Long id, String firstName, String lastName, Long positionId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.positionId = positionId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
