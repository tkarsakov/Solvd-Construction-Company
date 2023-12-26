package com.solvd.construction.service;

import com.solvd.construction.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);

    List<Employee> retrieveAll();
}
