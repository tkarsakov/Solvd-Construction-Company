package com.solvd.construction.service.impl;

import com.solvd.construction.model.Employee;
import com.solvd.construction.persistence.EmployeeRepository;
import com.solvd.construction.persistence.impl.EmployeeRepositoryImplDAO;
import com.solvd.construction.service.EmployeeService;
import com.solvd.construction.service.PositionService;

import java.util.List;
import java.util.function.Consumer;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PositionService positionService;

    public EmployeeServiceImpl() {
        this.employeeRepository = new EmployeeRepositoryImplDAO();
        this.positionService = new PositionServiceImpl();
    }

    @Override
    public Employee create(Employee employee) {
        employee.setId(null);
        employee.setPosition(positionService.retrieveById(employee.getPositionId()).orElse(null));
        employeeRepository.create(employee);
        return employee;
    }

    @Override
    public List<Employee> retrieveAll() {
        return employeeRepository.findAll().stream().peek(setFields()).toList();
    }

    @Override
    public List<Employee> retrieveAllByProjectId(Long projectId) {
        return employeeRepository.findAllByProjectId(projectId).stream().peek(setFields()).toList();
    }

    private Consumer<Employee> setFields() {
        return employee -> employee.setPosition(positionService.retrieveById(employee.getPositionId()).orElse(null));
    }
}
