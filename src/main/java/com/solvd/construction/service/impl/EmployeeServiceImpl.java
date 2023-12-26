package com.solvd.construction.service.impl;

import com.solvd.construction.model.Employee;
import com.solvd.construction.persistence.EmployeeRepository;
import com.solvd.construction.persistence.impl.EmployeeRepositoryImplDAO;
import com.solvd.construction.service.EmployeeService;
import com.solvd.construction.service.PositionService;

import java.util.List;

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
        employeeRepository.create(employee);
        return null;
    }

    @Override
    public List<Employee> retrieveAll() {
        return employeeRepository.findAll();
    }
}
