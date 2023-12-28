package com.solvd.construction.persistence;

import com.solvd.construction.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    void create(Employee employee);

    Optional<Employee> findById(Long id);

    List<Employee> findAll();

    void update(Employee employee);

    void deleteById(Long id);

    List<Employee> findAllByProjectId(Long projectId);
}
