package com.solvd.construction.service.impl;

import com.solvd.construction.model.Employee;
import com.solvd.construction.service.EmployeeService;
import com.solvd.construction.persistence.mappers.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.function.Consumer;

public class MBEmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final MBPositionServiceImpl positionService;

    public MBEmployeeServiceImpl(SqlSession session) {
        this.employeeMapper = session.getMapper(EmployeeMapper.class);
        this.positionService = new MBPositionServiceImpl(session);
    }

    @Override
    public Employee create(Employee employee) {
        employee.setId(null);
        if (positionService.retrieveByPositionName(employee.getPosition().getPositionName()).isEmpty()) {
            positionService.create(employee.getPosition());
        }
        return employeeMapper.create(employee);
    }

    @Override
    public List<Employee> retrieveAll() {
        List<Employee> employeeList = employeeMapper.retrieveAll();
        employeeList.forEach(setFields());
        return employeeList;
    }

    @Override
    public List<Employee> retrieveAllByProjectId(Long id) {
        List<Employee> employeeList = employeeMapper.retrieveAllByProjectId(id);
        employeeList.forEach(setFields());
        return employeeList;
    }

    private Consumer<Employee> setFields() {
        return employee -> employee.setPosition(positionService.retrieveById(employee.getPositionId()).orElse(null));
    }
}
