package com.solvd.construction.service;

import com.solvd.construction.model.Employee;

import java.util.List;

public interface EmployeeService extends IService<Employee> {
    Employee create(Employee employee);

    List<Employee> retrieveAll();

    List<Employee> retrieveAllByProjectId(Long id);
}
